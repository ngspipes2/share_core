package pt.isel.ngspipes.share_core.logic.service.group;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import pt.isel.ngspipes.share_core.dataAccess.RepositoryException;
import pt.isel.ngspipes.share_core.dataAccess.group.IGroupRepository;
import pt.isel.ngspipes.share_core.logic.domain.Group;
import pt.isel.ngspipes.share_core.logic.domain.Image;
import pt.isel.ngspipes.share_core.logic.service.ICurrentUserSupplier;
import pt.isel.ngspipes.share_core.logic.service.IService;
import pt.isel.ngspipes.share_core.logic.service.Service;
import pt.isel.ngspipes.share_core.logic.service.ServiceUtils;
import pt.isel.ngspipes.share_core.logic.service.exceptions.NonExistentEntityException;
import pt.isel.ngspipes.share_core.logic.service.exceptions.ServiceException;

import java.util.Collection;
import java.util.Date;

@org.springframework.stereotype.Service
public class GroupService extends Service<Group, String> implements IGroupService {

    private static final String IMAGE_PREFIX = "Group";



    @Autowired
    private IGroupRepository repository;
    @Autowired
    private ICurrentUserSupplier currentUserSupplier;
    @Autowired
    private IService<Image, String> imageService;



    public GroupService() {
        super("Groups", "Group");
    }



    @Override
    @Transactional
    public void insert(Group group) throws ServiceException {
        group.setCreationDate(new Date());
        group.setOwner(currentUserSupplier.get());

        super.insert(group);
    }

    @Override
    @Transactional
    public void delete(String groupName) throws ServiceException {
        super.delete(groupName);

        if(imageService.getById(IMAGE_PREFIX + groupName) != null)
            imageService.delete(IMAGE_PREFIX + groupName);
    }

    @Override
    protected void validateInsert(Group group) throws ServiceException { }

    @Override
    protected void validateDelete(String key) throws ServiceException { }

    @Override
    protected void validateUpdate(Group group) throws ServiceException {
        Group savedGroup = getById(group.getGroupName());

        if(savedGroup != null) {
            if(!ServiceUtils.sameDate(savedGroup.getCreationDate(), group.getCreationDate()))
                throw new ServiceException("Group's creationDate Cannot be changed!");

            if(!savedGroup.getOwner().getUserName().equals(group.getOwner().getUserName()))
                throw new ServiceException("Group's owner Cannot be changed!");
        }
    }

    @Override
    protected String getId(Group group) throws ServiceException {
        return group.getGroupName();
    }

    @Override
    public Collection<Group> getGroupsOfUser(String userName) throws ServiceException {
        try{
            return repository.getGroupsOfUser(userName);
        } catch (RepositoryException e) {
            throw new ServiceException("Error getting groups of User!", e);
        }
    }

    @Override
    public Collection<String> getGroupsNames() throws ServiceException {
        try {
            return repository.getGroupsNames();
        } catch(RepositoryException e) {
            throw new ServiceException("Error getting groups names!", e);
        }
    }

    @Override
    public Image getGroupImage(String groupName) throws ServiceException {
        if(getById(groupName) == null)
            return null;

        return imageService.getById(IMAGE_PREFIX + groupName);
    }

    @Override
    public void setGroupImage(String groupName, Image image) throws ServiceException {
        if(getById(groupName) == null)
            throw new NonExistentEntityException("There is no group:" + groupName + "!");

        image.setId(IMAGE_PREFIX + groupName);

        if(imageService.getById(image.getId()) == null)
            imageService.insert(image);
        else
            imageService.update(image);
    }

    @Override
    @Transactional
    public void deleteGroupsOfUser(String userName) throws ServiceException {
        try {
            for(Group group : repository.getGroupsOfUser(userName))
                if(imageService.getById(IMAGE_PREFIX + group.getGroupName()) != null)
                    imageService.delete(IMAGE_PREFIX + group.getGroupName());

            repository.deleteGroupsOfUser(userName);
        } catch (RepositoryException e) {
            throw new ServiceException("Error deleting groups of user:" + userName + "!", e);
        }
    }

}
