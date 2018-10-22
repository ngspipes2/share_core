package pt.isel.ngspipes.share_core.logic.service.permission;

import org.springframework.stereotype.Service;
import pt.isel.ngspipes.share_core.logic.domain.GroupMember;
import pt.isel.ngspipes.share_core.logic.domain.User;
import pt.isel.ngspipes.share_core.logic.service.exceptions.ServiceException;

@Service
public class GroupMemberPermissionService extends PermissionService<GroupMember, Integer> {

    public GroupMemberPermissionService() {
        super(true, true, true);
    }



    @Override
    protected boolean isValid(GroupMember entity, User user, Access<Integer> access) throws ServiceException {
        return entity.getGroup().getOwner().getUserName().equals(access.userName);
    }

}
