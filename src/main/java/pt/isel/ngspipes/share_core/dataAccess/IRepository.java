package pt.isel.ngspipes.share_core.dataAccess;

import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface IRepository<T, K> {

    Collection<T> getAll() throws RepositoryException;

    T getById(K k) throws RepositoryException;

    void insert(T t) throws RepositoryException;

    void delete(K k) throws RepositoryException;

    void update(T t) throws RepositoryException;

}
