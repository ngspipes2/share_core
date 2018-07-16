package pt.isel.ngspipes.share_core.logic.service.toolsRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import pt.isel.ngspipes.share_core.dataAccess.RepositoryException;
import pt.isel.ngspipes.share_core.dataAccess.toolsRepository.IToolsRepositoryRepository;
import pt.isel.ngspipes.share_core.logic.domain.ToolsRepository;
import pt.isel.ngspipes.share_core.logic.service.ICurrentUserSupplier;
import pt.isel.ngspipes.share_core.logic.service.Service;
import pt.isel.ngspipes.share_core.logic.service.ServiceUtils;
import pt.isel.ngspipes.share_core.logic.service.exceptions.ServiceException;

import java.util.Collection;
import java.util.Date;

@org.springframework.stereotype.Service
public class ToolsRepositoryService extends Service<ToolsRepository, Integer> implements IToolsRepositoryService {

    @Autowired
    private IToolsRepositoryRepository toolsRepositoryRepository;
    @Autowired
    private ICurrentUserSupplier currentUserSupplier;



    protected ToolsRepositoryService() {
        super("ToolsRepositories", "ToolsRepository");
    }



    @Override
    @Transactional
    public void insert(ToolsRepository repository) throws ServiceException {
        repository.setCreationDate(new Date());
        repository.setOwner(currentUserSupplier.get());

        super.insert(repository);
    }

    @Override
    protected void validateInsert(ToolsRepository repository) throws ServiceException { }

    @Override
    protected void validateDelete(Integer id) throws ServiceException { }

    @Override
    protected void validateUpdate(ToolsRepository repository) throws ServiceException {
        ToolsRepository savedRepository = getById(repository.getId());

        if(repository != null) {
            if(!ServiceUtils.sameCreationDate(savedRepository.getCreationDate(), repository.getCreationDate()))
                throw new ServiceException("ToolsRepository's creationDate Cannot be changed!");

            if(!savedRepository.getOwner().getUserName().equals(repository.getOwner().getUserName()))
                throw new ServiceException("ToolsRepository's owner Cannot be changed!");
        }
    }

    @Override
    protected Integer getId(ToolsRepository repository) throws ServiceException {
        return repository.getId();
    }

    @Override
    public Collection<ToolsRepository> getToolsRepositoriesOfUser(String userName) throws ServiceException {
        try {
            return toolsRepositoryRepository.getRepositoriesOfUser(userName);
        } catch (RepositoryException e) {
            throw new ServiceException("Error getting Repositories of User!");
        }
    }

}
