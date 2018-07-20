package pt.isel.ngspipes.share_core.dataAccess.toolsRepository;


import pt.isel.ngspipes.share_core.dataAccess.PostgresRepository;
import pt.isel.ngspipes.share_core.dataAccess.RepositoryException;
import pt.isel.ngspipes.share_core.logic.domain.ToolsRepository;

import java.util.Collection;
import java.util.stream.Collectors;

@org.springframework.stereotype.Repository
public class ToolsRepositoryRepository extends PostgresRepository<ToolsRepository, Integer> implements IToolsRepositoryRepository {

    public ToolsRepositoryRepository() {
        super(ToolsRepository.class);
    }



    @Override
    protected void setId(ToolsRepository repository, Integer id) {
        repository.setId(id);
    }

    @Override
    protected Integer getId(ToolsRepository repository) {
        return repository.getId();
    }

    @Override
    protected void merge(ToolsRepository repository, ToolsRepository repositoryToUpdate) {
        repositoryToUpdate.setId(repository.getId());
        repositoryToUpdate.setName(repository.getName());
        repositoryToUpdate.setDescription(repository.getDescription());
        repositoryToUpdate.setCreationDate(repository.getCreationDate());
        repositoryToUpdate.setPublic(repository.isPublic());
        repositoryToUpdate.setOwner(repository.getOwner());
        repositoryToUpdate.setGroupsAccess(repository.getGroupsAccess());
        repositoryToUpdate.setUsersAccess(repository.getUsersAccess());
    }

    @Override
    public Collection<ToolsRepository> getRepositoriesOfUser(String userName) throws RepositoryException {
        return getAll().stream().filter((repository) -> repository.getOwner().getUserName().equals(userName)).collect(Collectors.toList());
    }

}
