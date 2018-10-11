package pt.isel.ngspipes.share_core.logic.service.permission;

import pt.isel.ngspipes.share_core.logic.domain.User;
import pt.isel.ngspipes.share_core.logic.service.exceptions.ServiceException;
import pt.isel.ngspipes.share_core.logic.service.permission.Access;
import pt.isel.ngspipes.share_core.logic.service.permission.PermissionService;
import pt.isel.ngspipes.share_core.logic.domain.ToolsRepositoryMetadata;

public class ToolsRepositoryMetadataPermissionService extends PermissionService<ToolsRepositoryMetadata, Integer> {

    public ToolsRepositoryMetadataPermissionService(boolean allowGetAccesses, boolean allowAdminAccesses, boolean allowNonExistentEntityAccess) {
        super(allowGetAccesses, allowAdminAccesses, allowNonExistentEntityAccess);
    }



    @Override
    protected boolean isValid(ToolsRepositoryMetadata entity, User user, Access<Integer> access) throws ServiceException {
        return entity.getOwner().getUserName().equals(access.userName);
    }

}
