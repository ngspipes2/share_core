package pt.isel.ngspipes.share_core.logic.service.pipelinesRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import pt.isel.ngspipes.share_core.dataAccess.RepositoryException;
import pt.isel.ngspipes.share_core.dataAccess.pipelinesRepository.IPipelinesRepositoryRepository;
import pt.isel.ngspipes.share_core.logic.domain.PipelinesRepository;
import pt.isel.ngspipes.share_core.logic.service.ICurrentUserSupplier;
import pt.isel.ngspipes.share_core.logic.service.Service;
import pt.isel.ngspipes.share_core.logic.service.ServiceUtils;
import pt.isel.ngspipes.share_core.logic.service.exceptions.ServiceException;

import java.util.Collection;
import java.util.Date;

@org.springframework.stereotype.Service
public class PipelinesRepositoryService extends Service<PipelinesRepository, Integer> implements IPipelinesRepositoryService {

    @Autowired
    private IPipelinesRepositoryRepository pipelinesRepositoryRepository;
    @Autowired
    private ICurrentUserSupplier currentUserSupplier;



    protected PipelinesRepositoryService() {
        super("PipelinesRepositories", "PipelinesRepository");
    }



    @Override
    @Transactional
    public void insert(PipelinesRepository repository) throws ServiceException {
        repository.setCreationDate(new Date());
        repository.setOwner(currentUserSupplier.get());

        super.insert(repository);
    }

    @Override
    protected void validateInsert(PipelinesRepository repository) throws ServiceException { }

    @Override
    protected void validateDelete(Integer id) throws ServiceException { }

    @Override
    protected void validateUpdate(PipelinesRepository repository) throws ServiceException {
        PipelinesRepository savedRepository = getById(repository.getId());

        if(repository != null) {
            if(!ServiceUtils.sameCreationDate(savedRepository.getCreationDate(), repository.getCreationDate()))
                throw new ServiceException("ToolsRepository's creationDate Cannot be changed!");

            if(!savedRepository.getOwner().getUserName().equals(repository.getOwner().getUserName()))
                throw new ServiceException("ToolsRepository's owner Cannot be changed!");
        }
    }

    @Override
    protected Integer getId(PipelinesRepository repository) throws ServiceException {
        return repository.getId();
    }

    @Override
    public Collection<PipelinesRepository> getPipelinesRepositoriesOfUser(String userName) throws ServiceException {
        try {
            return pipelinesRepositoryRepository.getRepositoriesOfUser(userName);
        } catch (RepositoryException e) {
            throw new ServiceException("Error getting Repositories of User!");
        }
    }

}
