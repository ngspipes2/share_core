package pt.isel.ngspipes.share_core.logic.service.repositoryInfo;

import pt.isel.ngspipes.share_core.logic.domain.RepositoryInfo;
import pt.isel.ngspipes.share_core.logic.service.IService;
import pt.isel.ngspipes.share_core.logic.service.exceptions.ServiceException;

import java.util.Collection;

public interface IRepositoryInfoService extends IService<RepositoryInfo, String> {

    Collection<RepositoryInfo> getRepositoriesOfUser(String userName) throws ServiceException;
    void deleteRepositoriesOfUser(String userName) throws ServiceException;
    Collection<String> getRepositoriesNames() throws ServiceException;
}
