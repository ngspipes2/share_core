package pt.isel.ngspipes.share_core.dataAccess;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.Collection;

@Repository
@Transactional
public abstract class PostgresRepository<T, K extends Serializable> implements IRepository<T, K> {

    protected Class<T> klass;
    @Autowired
    protected HibernateTemplate hibernateTemplate;



    public PostgresRepository(Class<T> klass) {
        this.klass = klass;
    }



    @Override
    public Collection<T> getAll() throws RepositoryException {
        return this.hibernateTemplate.loadAll(klass);
    }

    @Override
    public T getById(K key) throws RepositoryException {
        return this.hibernateTemplate.get(klass, key);
    }

    @Override
    public void insert(T entity) throws RepositoryException {
        T createdEntity = this.hibernateTemplate.merge(entity);
        setId(entity, getId(createdEntity));
    }

    @Override
    public void delete(K key) throws RepositoryException {
        T entity = getById(key);
        this.hibernateTemplate.delete(entity);
    }

    @Override
    public void update(T entity) throws RepositoryException {
        T entityToUpdate = getById(getId(entity));

        merge(entity, entityToUpdate);

        this.hibernateTemplate.update(entityToUpdate);
    }

    protected Collection<T> find(Criterion... criterions) {
        Session session = this.hibernateTemplate.getSessionFactory().getCurrentSession();
        Criteria criteria = session.createCriteria(klass);

        for(Criterion criterion : criterions)
            criteria = criteria.add(criterion);

        return criteria.list();
    }


    protected abstract void setId(T t, K id);
    protected abstract K getId(T t);
    protected abstract void  merge(T entity, T entityToUpdate);

}
