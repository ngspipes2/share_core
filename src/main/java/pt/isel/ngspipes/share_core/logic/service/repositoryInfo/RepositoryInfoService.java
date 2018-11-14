package pt.isel.ngspipes.share_core.logic.service.repositoryInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import pt.isel.ngspipes.share_core.dataAccess.RepositoryException;
import pt.isel.ngspipes.share_core.dataAccess.repositoryInfo.IRepositoryInfoRepository;
import pt.isel.ngspipes.share_core.logic.domain.RepositoryInfo;
import pt.isel.ngspipes.share_core.logic.service.ICurrentUserSupplier;
import pt.isel.ngspipes.share_core.logic.service.Service;
import pt.isel.ngspipes.share_core.logic.service.ServiceUtils;
import pt.isel.ngspipes.share_core.logic.service.exceptions.ServiceException;

import java.util.Collection;
import java.util.Date;

@org.springframework.stereotype.Service
public class RepositoryInfoService extends Service<RepositoryInfo, String> implements IRepositoryInfoService {

    @Autowired
    private IRepositoryInfoRepository repositoryInfoRepository;
    @Autowired
    private ICurrentUserSupplier currentUserSupplier;
    @Autowired
    private IRepositoryService repositoryService;



    public RepositoryInfoService() {
        super("RepositoriesInfo", "RepositoryInfo");
    }



    @Override
    @Transactional
    public void insert(RepositoryInfo repository) throws ServiceException {
        repository.setCreationDate(new Date());
        repository.setOwner(currentUserSupplier.get());

        if(repository.getLocationType().equals(RepositoryInfo.LocationType.INTERNAL))
            repository.setLocation("{server}" + "/" + repository.getRepositoryName());

        super.insert(repository);

        if(repository.getLocationType().equals(RepositoryInfo.LocationType.INTERNAL))
            repositoryService.createRepository(repository);
    }

    @Override
    @Transactional
    public void delete(String repositoryName) throws ServiceException {
        RepositoryInfo repository = getById(repositoryName);

        super.delete(repositoryName);

        if(repository.getLocationType().equals(RepositoryInfo.LocationType.INTERNAL))
            repositoryService.deleteRepository(repository);
    }

    @Override
    protected void validateInsert(RepositoryInfo repository) throws ServiceException {
        String acceptedCharactersRegex = "[a-zA-Z0-9\\-_]+";
        if(!repository.getRepositoryName().matches(acceptedCharactersRegex))
            throw new ServiceException("RepositoryName can only contain these characters " + acceptedCharactersRegex);
    }

    @Override
    protected void validateDelete(String repositoryName) throws ServiceException { }

    @Override
    protected void validateUpdate(RepositoryInfo repository) throws ServiceException {
        RepositoryInfo savedRepository = getById(repository.getRepositoryName());

        if(savedRepository != null) {
            if(!ServiceUtils.sameDate(savedRepository.getCreationDate(), repository.getCreationDate()))
                throw new ServiceException("RepositoryInfo's creationDate cannot be changed!");

            if(!savedRepository.getOwner().getUserName().equals(repository.getOwner().getUserName()))
                throw new ServiceException("RepositoryInfo's owner cannot be changed!");

            if(!savedRepository.getEntityType().equals(repository.getEntityType()))
                throw new ServiceException("RepositoryInfo's entity type cannot be changed!");

            if(!savedRepository.getLocationType().equals(repository.getLocationType()))
                throw new ServiceException("RepositoryInfo's location type cannot be changed!");

            if(savedRepository.getLocationType().equals(RepositoryInfo.LocationType.INTERNAL) && !savedRepository.getLocation().equals(repository.getLocation()))
                throw new ServiceException("Location of internal repositories cannot be changed!");
        }
    }

    @Override
    protected String getId(RepositoryInfo repository) throws ServiceException {
        return repository.getRepositoryName();
    }

    @Override
    public Collection<RepositoryInfo> getRepositoriesOfUser(String userName) throws ServiceException {
        try {
            return repositoryInfoRepository.getRepositoriesOfUser(userName);
        } catch (RepositoryException e) {
            throw new ServiceException("Error getting Repositories of User!");
        }
    }

    @Override
    @Transactional
    public void deleteRepositoriesOfUser(String userName) throws ServiceException {
        try {
            for(RepositoryInfo repo : repositoryInfoRepository.getRepositoriesOfUser(userName))
                if(repo.getLocationType().equals(RepositoryInfo.LocationType.INTERNAL))
                    repositoryService.deleteRepository(repo);

            repositoryInfoRepository.deleteRepositoriesOfUser(userName);
        } catch (RepositoryException e) {
            throw new ServiceException("Error deleting repositories of user:" + userName + "!", e);
        }
    }

    @Override
    public Collection<String> getRepositoriesNames() throws ServiceException {
        try {
            return repositoryInfoRepository.getRepositoriesNames();
        } catch (RepositoryException e) {
            throw new ServiceException("Error getting repositories names!", e);
        }
    }

}
