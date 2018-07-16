package pt.isel.ngspipes.share_core.dataAccess.toolsRepository;

import pt.isel.ngspipes.share_core.dataAccess.IRepository;
import pt.isel.ngspipes.share_core.dataAccess.RepositoryException;
import pt.isel.ngspipes.share_core.logic.domain.ToolsRepository;

import java.util.Collection;

public interface IToolsRepositoryRepository extends IRepository<ToolsRepository, Integer> {

    Collection<ToolsRepository> getRepositoriesOfUser(String userName) throws RepositoryException;

}
