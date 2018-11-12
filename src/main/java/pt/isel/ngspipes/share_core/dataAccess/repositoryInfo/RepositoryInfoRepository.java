package pt.isel.ngspipes.share_core.dataAccess.repositoryInfo;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import pt.isel.ngspipes.share_core.dataAccess.PostgresRepository;
import pt.isel.ngspipes.share_core.dataAccess.RepositoryException;
import pt.isel.ngspipes.share_core.logic.domain.RepositoryInfo;

import java.util.Collection;
import java.util.stream.Collectors;

@org.springframework.stereotype.Repository
public class RepositoryInfoRepository extends PostgresRepository<RepositoryInfo, String> implements IRepositoryInfoRepository {

    public RepositoryInfoRepository() {
        super(RepositoryInfo.class);
    }



    @Override
    protected void setId(RepositoryInfo repository, String repositoryName) {
        repository.setRepositoryName(repositoryName);
    }

    @Override
    protected String getId(RepositoryInfo repository) {
        return repository.getRepositoryName();
    }

    @Override
    protected void merge(RepositoryInfo repository, RepositoryInfo repositoryToUpdate) {
        repositoryToUpdate.setRepositoryName(repository.getRepositoryName());
        repositoryToUpdate.setDescription(repository.getDescription());
        repositoryToUpdate.setCreationDate(repository.getCreationDate());
        repositoryToUpdate.setPublic(repository.getIsPublic());
        repositoryToUpdate.setOwner(repository.getOwner());
        repositoryToUpdate.setEntityType(repository.getEntityType());
        repositoryToUpdate.setLocationType(repository.getLocationType());
        repositoryToUpdate.setLocation(repository.getLocation());
    }

    @Override
    public Collection<RepositoryInfo> getRepositoriesOfUser(String userName) throws RepositoryException {
        return getAll().stream().filter((repository) -> repository.getOwner().getUserName().equals(userName)).collect(Collectors.toList());
    }

    @Override
    public void deleteRepositoriesOfUser(String userName) throws RepositoryException {
        super.hibernateTemplate.deleteAll(getRepositoriesOfUser(userName));
    }

    @Override
    public Collection<String> getRepositoriesNames() throws RepositoryException {
        Session session = this.hibernateTemplate.getSessionFactory().getCurrentSession();
        Criteria criteria = session.createCriteria(RepositoryInfo.class);
        criteria.setProjection(Projections.projectionList().add(Projections.property("repositoryName")));

        return criteria.list();
    }

}
