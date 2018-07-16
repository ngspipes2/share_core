package pt.isel.ngspipes.share_core.logic.service.toolsRepository;

import pt.isel.ngspipes.share_core.logic.domain.ToolsRepository;
import pt.isel.ngspipes.share_core.logic.service.IService;
import pt.isel.ngspipes.share_core.logic.service.exceptions.ServiceException;

import java.util.Collection;

public interface IToolsRepositoryService extends IService<ToolsRepository, Integer> {

    Collection<ToolsRepository> getToolsRepositoriesOfUser(String userName) throws ServiceException;

}
