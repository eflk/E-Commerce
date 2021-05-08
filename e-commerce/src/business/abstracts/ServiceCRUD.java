package business.abstracts;

import java.util.List;
import java.util.function.Predicate;

import core.results.abstracts.Result;
import entities.abstracts.Entity;

public interface ServiceCRUD<T extends Entity<T>> {
	Result add(T entity);

	Result update(T entity);

	Result delete(T entity);

	T get(int id);
	
	public T get(Predicate<? super T> filter);
	
	public List<T> getList(Predicate<? super T> filter);
	
	List<T> getAll();
}
