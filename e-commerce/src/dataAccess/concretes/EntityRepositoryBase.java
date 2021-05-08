package dataAccess.concretes;

import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import dataAccess.abstracts.EntityRepository;
import entities.abstracts.Entity;

public class EntityRepositoryBase<T extends Entity<T>> implements EntityRepository<T> {
	List<T> _context;

	public EntityRepositoryBase(List<T> context) {
		_context = context;
	}

	private int getNewId() {
		int latestId = 0;
		try {
			Comparator<T> comparator = Comparator.comparing(T::getId);
			latestId = _context.stream().max(comparator).get().getId();
		} catch (Exception e) {
		}
		return latestId == 0 ? 1 : latestId + 1;

	}

	@Override
	public void add(T entity) throws Exception {
		if (this.get(entity.getId()) != null)
			throw new Exception("The id \"" + entity.getId() + "\" already exists.");
		entity.setId(getNewId());
		_context.add(entity);
	}

	@Override
	public void update(T entity) throws Exception {
		if (entity != null) {
			T entityToUpdate = this.get(entity.getId());
			if (entityToUpdate == null)
				throw new Exception("Not found");
			entityToUpdate.clone(entity);
		} else {
			throw new Exception("Not found");
		}

	}

	@Override
	public void delete(T entity) throws Exception {
		if (entity != null) {
			if (!_context.removeIf(p -> p.getId() == entity.getId()))
				throw new Exception("Not found");
		} else {
			throw new Exception("Not found");
		}
	}

	@Override
	public List<T> getAll() {
		return _context;
	}

	@Override
	public T get(int id) {
		try {
			return this.get(p -> p.getId() == id).get(0);
		} catch (Exception ex) {
		}
		return null;

	}

	@Override
	public List<T> get(Predicate<? super T> filter) {
		return _context.stream().filter(filter).collect(Collectors.<T>toList());
	}
}
