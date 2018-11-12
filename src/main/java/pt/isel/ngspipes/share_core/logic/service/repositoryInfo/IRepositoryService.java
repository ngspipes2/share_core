package pt.isel.ngspipes.share_core.logic.service.repositoryInfo;

import org.springframework.stereotype.Service;
import pt.isel.ngspipes.pipeline_repository.IPipelinesRepository;
import pt.isel.ngspipes.share_core.logic.domain.RepositoryInfo;
import pt.isel.ngspipes.share_core.logic.service.exceptions.ServiceException;
import pt.isel.ngspipes.tool_repository.interfaces.IToolsRepository;

@Service
public interface IRepositoryService {

    void createRepository(RepositoryInfo repository) throws ServiceException;

    void deleteRepository(RepositoryInfo repository) throws ServiceException;

    IToolsRepository getToolsRepository(RepositoryInfo repository) throws ServiceException;

    IPipelinesRepository getPipelinesRepository(RepositoryInfo repository) throws ServiceException;

}
