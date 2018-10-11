package pt.isel.ngspipes.share_core.logic.service.pipelinesRepositoryMetadata;

import pt.isel.ngspipes.share_core.logic.domain.PipelinesRepositoryMetadata;
import pt.isel.ngspipes.share_core.logic.service.IService;
import pt.isel.ngspipes.share_core.logic.service.exceptions.ServiceException;

import java.util.Collection;

public interface IPipelinesRepositoryMetadataService extends IService<PipelinesRepositoryMetadata, Integer> {

    Collection<PipelinesRepositoryMetadata> getPipelinesRepositoriesOfUser(String userName) throws ServiceException;

}
