package pt.isel.ngspipes.share_core.logic.service.permission;

import org.springframework.stereotype.Service;
import pt.isel.ngspipes.share_core.logic.domain.AccessToken;
import pt.isel.ngspipes.share_core.logic.domain.User;
import pt.isel.ngspipes.share_core.logic.service.exceptions.ServiceException;

@Service
public class AccessTokenPermissionService extends PermissionService<AccessToken, Integer> {

    public AccessTokenPermissionService() {
        super(true, true, true);
    }



    @Override
    protected boolean isValid(AccessToken entity, User user, Access<Integer> access) throws ServiceException {
        return entity.getOwner().getUserName().equals(access.userName);
    }

}
