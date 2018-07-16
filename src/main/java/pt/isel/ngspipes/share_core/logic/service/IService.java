package pt.isel.ngspipes.share_core.logic.service;

import org.springframework.stereotype.Service;
import pt.isel.ngspipes.share_core.logic.service.exceptions.ServiceException;

import java.util.Collection;

@Service
public interface IService<T, K> {

    Collection<T> getAll() throws ServiceException;

    T getById(K key) throws ServiceException;

    void insert(T element) throws ServiceException;

    void delete(K key) throws ServiceException;

    void update(T element) throws ServiceException;

}
