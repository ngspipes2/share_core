package pt.isel.ngspipes.share_core.dataAccess.pipelinesRepository;


import pt.isel.ngspipes.share_core.dataAccess.PostgresRepository;
import pt.isel.ngspipes.share_core.dataAccess.RepositoryException;
import pt.isel.ngspipes.share_core.logic.domain.PipelinesRepository;

import java.util.Collection;
import java.util.stream.Collectors;

@org.springframework.stereotype.Repository
public class PipelinesRepositoryRepository extends PostgresRepository<PipelinesRepository, Integer> implements IPipelinesRepositoryRepository {

    public PipelinesRepositoryRepository() {
        super(PipelinesRepository.class);
    }



    @Override
    protected void setId(PipelinesRepository repository, Integer id) {
        repository.setId(id);
    }

    @Override
    protected Integer getId(PipelinesRepository repository) {
        return repository.getId();
    }

    @Override
    protected void merge(PipelinesRepository repository, PipelinesRepository repositoryToUpdate) {
        repositoryToUpdate.setId(repository.getId());
        repositoryToUpdate.setName(repository.getName());
        repositoryToUpdate.setDescription(repository.getDescription());
        repositoryToUpdate.setCreationDate(repository.getCreationDate());
        repositoryToUpdate.setPublic(repository.isPublic());
        repositoryToUpdate.setOwner(repository.getOwner());
        repositoryToUpdate.setGroupAccess(repository.getGroupAccess());
        repositoryToUpdate.setUsersAccess(repository.getUsersAccess());
    }

    @Override
    public Collection<PipelinesRepository> getRepositoriesOfUser(String userName) throws RepositoryException {
        return getAll().stream().filter((repository) -> repository.getOwner().getUserName().equals(userName)).collect(Collectors.toList());
    }

}
