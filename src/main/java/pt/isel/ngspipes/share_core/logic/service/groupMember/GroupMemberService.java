package pt.isel.ngspipes.share_core.logic.service.groupMember;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import pt.isel.ngspipes.share_core.dataAccess.RepositoryException;
import pt.isel.ngspipes.share_core.dataAccess.groupMember.IGroupMemberRepository;
import pt.isel.ngspipes.share_core.logic.domain.GroupMember;
import pt.isel.ngspipes.share_core.logic.service.Service;
import pt.isel.ngspipes.share_core.logic.service.ServiceUtils;
import pt.isel.ngspipes.share_core.logic.service.exceptions.ServiceException;

import java.util.Collection;
import java.util.Date;

@org.springframework.stereotype.Service
public class GroupMemberService extends Service<GroupMember, Integer> implements IGroupMemberService {

    @Autowired
    private IGroupMemberRepository repository;



    public GroupMemberService() {
        super("GroupMembers", "GroupMember");
    }



    @Override
    @Transactional
    public void insert(GroupMember member) throws ServiceException {
        member.setCreationDate(new Date());
        super.insert(member);
    }

    @Override
    protected void validateInsert(GroupMember member) throws ServiceException { }

    @Override
    protected void validateDelete(Integer id) throws ServiceException { }

    @Override
    protected void validateUpdate(GroupMember member) throws ServiceException {
        GroupMember savedMember = getById(member.getId());

        if(savedMember != null) {
            if(!ServiceUtils.sameDate(savedMember.getCreationDate(), member.getCreationDate()))
                throw new ServiceException("Members's creationDate Cannot be changed!");

            if(!savedMember.getUser().getUserName().equals(member.getUser().getUserName()))
                throw new ServiceException("Members's user Cannot be changed!");

            if(!savedMember.getGroup().getGroupName().equals(member.getGroup().getGroupName()))
                throw new ServiceException("Members's group Cannot be changed!");
        }
    }

    @Override
    protected Integer getId(GroupMember member) throws ServiceException {
        return member.getId();
    }

    @Override
    public Collection<GroupMember> getMembersWithUser(String userName) throws ServiceException {
        try {
            return repository.getMembersWithUser(userName);
        } catch(RepositoryException e) {
            throw new ServiceException("Error getting Members with User!", e);
        }
    }

    @Override
    public Collection<GroupMember> getMembersOfGroup(String groupName) throws ServiceException {
        try {
            return repository.getMembersOfGroup(groupName);
        } catch(RepositoryException e) {
            throw new ServiceException("Error getting Members of Group!", e);
        }
    }

}
