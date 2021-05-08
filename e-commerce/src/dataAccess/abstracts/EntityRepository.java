package dataAccess.abstracts;

import entities.abstracts.Entity;

import java.util.List;
import java.util.function.Predicate;

public interface EntityRepository<T extends Entity<T>> {
    void add(T entity) throws Exception;

    void update(T entity) throws Exception;

    void delete(T entity) throws Exception;

    List<T> getAll();

    T get(int id);

    List<T> get(Predicate<? super T> filter);

}
