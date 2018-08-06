package pt.isel.ngspipes.share_core.logic.service.group;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import pt.isel.ngspipes.share_core.dataAccess.RepositoryException;
import pt.isel.ngspipes.share_core.dataAccess.group.IGroupRepository;
import pt.isel.ngspipes.share_core.logic.domain.Group;
import pt.isel.ngspipes.share_core.logic.service.ICurrentUserSupplier;
import pt.isel.ngspipes.share_core.logic.service.Service;
import pt.isel.ngspipes.share_core.logic.service.ServiceUtils;
import pt.isel.ngspipes.share_core.logic.service.exceptions.ServiceException;

import java.util.Collection;
import java.util.Date;

@org.springframework.stereotype.Service
public class GroupService extends Service<Group, String> implements IGroupService {

    @Autowired
    private IGroupRepository repository;
    @Autowired
    private ICurrentUserSupplier currentUserSupplier;



    protected GroupService() {
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
    protected void validateInsert(Group group) throws ServiceException { }

    @Override
    protected void validateDelete(String key) throws ServiceException { }

    @Override
    protected void validateUpdate(Group group) throws ServiceException {
        Group savedGroup = getById(group.getGroupName());

        if(savedGroup != null) {
            if(!ServiceUtils.sameCreationDate(savedGroup.getCreationDate(), group.getCreationDate()))
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
    public Collection<Group> getGroupsWithMember(String userName) throws ServiceException {
        try{
            return repository.getGroupsWithMember(userName);
        } catch (RepositoryException e) {
            throw new ServiceException("Error getting groups where User is member!", e);
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

}
