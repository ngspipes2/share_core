package pt.isel.ngspipes.share_core.dataAccess.repositoryInfo;

import pt.isel.ngspipes.share_core.dataAccess.IRepository;
import pt.isel.ngspipes.share_core.dataAccess.RepositoryException;
import pt.isel.ngspipes.share_core.logic.domain.RepositoryInfo;

import java.util.Collection;

public interface IRepositoryInfoRepository extends IRepository<RepositoryInfo, String> {

    Collection<RepositoryInfo> getRepositoriesOfUser(String userName) throws RepositoryException;
    void deleteRepositoriesOfUser(String userName) throws RepositoryException;
    Collection<String> getRepositoriesNames() throws RepositoryException;

}
