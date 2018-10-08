package pt.isel.ngspipes.share_core.logic.service.permission;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pt.isel.ngspipes.share_core.logic.domain.User;
import pt.isel.ngspipes.share_core.logic.service.IService;
import pt.isel.ngspipes.share_core.logic.service.exceptions.ServiceException;

@Service
public abstract class PermissionService<T, K> implements IPermissionService<T, K> {

    @Autowired
    protected IService<T, K> service;

    @Autowired
    protected IService<User, String> userService;

    protected boolean allowGetAccesses;
    protected boolean allowAdminAccesses;
    protected boolean allowNonExistentEntityAccess;



    public PermissionService(boolean allowGetAccesses, boolean allowAdminAccesses, boolean allowNonExistentEntityAccess) {
        this.allowGetAccesses = allowGetAccesses;
        this.allowAdminAccesses = allowAdminAccesses;
        this.allowNonExistentEntityAccess = allowNonExistentEntityAccess;
    }



    @Override
    @Transactional
    public boolean isValidAccess(Access<K> access) throws ServiceException {
        if(allowGetAccesses && access.operation.equals(Access.Operation.GET))
            return true;

        User user = access.userName == null ? null : userService.getById(access.userName);
        if(allowAdminAccesses && user != null && user.getRole().equals(User.Role.ADMIN))
            return true;

        T entity = access.entityId == null ? null : service.getById(access.entityId);

        if(allowNonExistentEntityAccess && entity == null)//accessing non existent entity
            return true;

        return isValid(entity, user, access);
    }



    protected abstract boolean isValid(T entity, User user, Access<K> access) throws ServiceException;

}
