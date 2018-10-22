package pt.isel.ngspipes.share_core.logic.service.permission;

import org.springframework.stereotype.Service;
import pt.isel.ngspipes.share_core.logic.domain.Group;
import pt.isel.ngspipes.share_core.logic.domain.User;
import pt.isel.ngspipes.share_core.logic.service.exceptions.ServiceException;

@Service
public class GroupPermissionService extends  PermissionService<Group, String> {

    public GroupPermissionService() {
        super(true, true, true);
    }



    @Override
    protected boolean isValid(Group entity, User user, Access<String> access) throws ServiceException {
        return entity.getOwner().getUserName().equals(access.userName);
    }

}
