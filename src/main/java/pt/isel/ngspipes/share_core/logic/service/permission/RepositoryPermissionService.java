package pt.isel.ngspipes.share_core.logic.service.permission;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pt.isel.ngspipes.share_core.logic.domain.*;
import pt.isel.ngspipes.share_core.logic.service.exceptions.ServiceException;
import pt.isel.ngspipes.share_core.logic.service.groupMember.IGroupMemberService;
import pt.isel.ngspipes.share_core.logic.service.repositoryGroupMember.IRepositoryGroupMemberService;
import pt.isel.ngspipes.share_core.logic.service.repositoryInfo.IRepositoryInfoService;
import pt.isel.ngspipes.share_core.logic.service.repositoryUserMember.IRepositoryUserMemberService;

@Service
public class RepositoryPermissionService implements IRepositoryPermissionService {

    @Autowired
    private IRepositoryInfoService repositoryInfoService;
    @Autowired
    private IRepositoryUserMemberService repositoryUserMemberService;
    @Autowired
    private IRepositoryGroupMemberService repositoryGroupMemberService;
    @Autowired
    private IGroupMemberService groupMemberService;



    @Override
    public boolean hasPermission(String repositoryName, User user, AccessToken token, Access.Operation operation) throws ServiceException {
        RepositoryInfo repository = repositoryInfoService.getById(repositoryName);

        if(repository == null)
            return true;

        return validateAccess(operation, repository, user, token);
    }

    private boolean validateAccess(Access.Operation operation, RepositoryInfo repository, User user, AccessToken token) throws ServiceException {
        if(operation.equals(Access.Operation.GET))
            return validateReadAccess(repository, user, token);
        else
            return validateWriteAccess(repository, user, token);
    }

    private boolean validateReadAccess(RepositoryInfo repository, User user, AccessToken token) throws ServiceException {
        if(repository.getIsPublic())
            return true;

        if(user == null)
            return false;

        if(user.getRole().equals(User.Role.ADMIN))
            return true;

        if(repository.getOwner().getUserName().equals(user.getUserName()))
            return true;

        if(isMemberOfRepository(user.getUserName(), repository.getRepositoryName()))
            return true;

        if(isMemberOfRepositoryGroupMember(user.getUserName(), repository.getRepositoryName()))
            return true;

        return false;
    }

    private boolean validateWriteAccess(RepositoryInfo repository, User user, AccessToken token) throws ServiceException {
        if(user == null)
            return false;

        if(token != null && !token.getWriteAccess())
            return false;

        String userName = user.getUserName();
        User.Role userRole = user.getRole();
        String repositoryName = repository.getRepositoryName();

        if(userRole.equals(User.Role.ADMIN))
            return true;

        if(repository.getOwner().getUserName().equals(userName))
            return true;

        if(isMemberOfRepository(userName, repositoryName)) {
            RepositoryUserMember member = getRepositoryUserMember(repositoryName, userName);
            if(member.getWriteAccess())
                return true;
        }

        for(RepositoryGroupMember member : repositoryGroupMemberService.getMembersOfRepository(repositoryName)) {
            if(!member.getWriteAccess())
                continue;

            for(GroupMember groupMember : groupMemberService.getMembersOfGroup(member.getGroup().getGroupName())){
                if(isMemberOfGroup(userName, groupMember.getGroup().getGroupName())) {
                    if(getGroupMember(groupMember.getGroup().getGroupName(), userName).getWriteAccess())
                        return true;
                }
            }
        }

        return false;
    }

    private boolean isMemberOfGroup(String userName, String groupName) throws ServiceException {
        return getGroupMember(groupName, userName) != null;
    }

    private GroupMember getGroupMember(String groupName, String userName) throws ServiceException {
        for(GroupMember groupMember : groupMemberService.getMembersOfGroup(groupName))
            if(groupMember.getUser().getUserName().equals(userName))
                return groupMember;

        return null;
    }

    private boolean isMemberOfRepositoryGroupMember(String userName, String repositoryName) throws ServiceException {
        for(RepositoryGroupMember member : repositoryGroupMemberService.getMembersOfRepository(repositoryName))
            if(isMemberOfGroup(userName, member.getGroup().getGroupName()))
                return true;

        return false;
    }

    private boolean isMemberOfRepository(String userName, String repositoryName) throws ServiceException {
        return getRepositoryUserMember(repositoryName, userName) != null;
    }

    private RepositoryUserMember getRepositoryUserMember(String repositoryName, String userName) throws ServiceException {
        for(RepositoryUserMember member : repositoryUserMemberService.getMembersOfRepository(repositoryName))
            if(member.getUser().getUserName().equals(userName))
                return member;

        return null;
    }

}
