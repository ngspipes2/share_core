package pt.isel.ngspipes.share_core.logic.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import pt.isel.ngspipes.share_core.dataAccess.IRepository;
import pt.isel.ngspipes.share_core.dataAccess.RepositoryException;
import pt.isel.ngspipes.share_core.logic.service.exceptions.DuplicatedEntityException;
import pt.isel.ngspipes.share_core.logic.service.exceptions.NonExistentEntityException;
import pt.isel.ngspipes.share_core.logic.service.exceptions.ServiceException;

import java.util.Collection;

@org.springframework.stereotype.Service
public abstract class Service<T, K> implements IService<T, K> {

    @Autowired
    private IRepository<T, K> repository;
    private String entitiesName;
    private String entityName;



    protected Service(String entitiesName, String entityName){
        this.repository = repository;
        this.entitiesName = entitiesName;
        this.entityName = entityName;
    }



    public Collection<T> getAll() throws ServiceException {
        try{
            return repository.getAll();
        } catch (RepositoryException e) {
            throw new ServiceException("Error getting all " + entitiesName + "!", e);
        }
    }

    public T getById(K key) throws ServiceException {
        try{
            return repository.getById(key);
        } catch (RepositoryException e) {
            throw new ServiceException("Error getting " + entityName + "[" + key + "]!", e);
        }
    }

    @Transactional
    public void insert(T element) throws ServiceException {
        try{
            if(getById(getId(element)) != null)
                throw new DuplicatedEntityException("There is already a " + entityName + " with id:" + getId(element));

            validateInsert(element);
            repository.insert(element);
        } catch (RepositoryException e) {
            throw new ServiceException("Error inserting " + entityName + "[" + getId(element) + "]!", e);
        }
    }

    @Transactional
    public void delete(K key) throws ServiceException {
        try{
            if(getById(key) == null)
                throw new NonExistentEntityException("There is no " + entityName + " with id:" + key);

            validateDelete(key);
            repository.delete(key);
        } catch(RepositoryException e) {
            throw new ServiceException("Error deleting " + entityName + "[" + key + "]!", e);
        }
    }

    @Transactional
    public void update(T element) throws ServiceException {
        try{
            if(getById(getId(element)) == null)
                throw new NonExistentEntityException("There is no " + entityName + " with id:" + getId(element));

            validateUpdate(element);
            repository.update(element);
        } catch(RepositoryException e) {
            throw new ServiceException("Error updating " + entityName + "[" + getId(element) + "]!", e);
        }
    }



    protected abstract void validateInsert(T element) throws ServiceException;
    protected abstract void validateDelete(K key) throws ServiceException;
    protected abstract void validateUpdate(T element) throws ServiceException;
    protected abstract K getId(T element) throws ServiceException;

}
