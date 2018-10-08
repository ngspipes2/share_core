package pt.isel.ngspipes.share_core.logic.service.permission;

import pt.isel.ngspipes.share_core.logic.domain.User;
import pt.isel.ngspipes.share_core.logic.service.exceptions.ServiceException;

public class UserPermissionService extends PermissionService<User, String> {

    public UserPermissionService() {
        super(true, true, true);
    }



    @Override
    protected boolean isValid(User entity, User user, Access<String> access) throws ServiceException {
        return entity.getUserName().equals(access.userName);
    }

}
