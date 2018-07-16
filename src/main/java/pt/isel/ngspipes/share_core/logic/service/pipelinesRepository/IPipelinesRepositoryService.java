package pt.isel.ngspipes.share_core.logic.service.pipelinesRepository;

import pt.isel.ngspipes.share_core.logic.domain.PipelinesRepository;
import pt.isel.ngspipes.share_core.logic.service.IService;
import pt.isel.ngspipes.share_core.logic.service.exceptions.ServiceException;

import java.util.Collection;

public interface IPipelinesRepositoryService extends IService<PipelinesRepository, Integer> {

    Collection<PipelinesRepository> getPipelinesRepositoriesOfUser(String userName) throws ServiceException;

}
