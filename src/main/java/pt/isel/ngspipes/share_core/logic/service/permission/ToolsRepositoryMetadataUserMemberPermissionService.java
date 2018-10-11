package pt.isel.ngspipes.share_core.logic.service.permission;

import pt.isel.ngspipes.share_core.logic.domain.User;
import pt.isel.ngspipes.share_core.logic.service.exceptions.ServiceException;
import pt.isel.ngspipes.share_core.logic.service.permission.Access;
import pt.isel.ngspipes.share_core.logic.service.permission.PermissionService;
import pt.isel.ngspipes.share_core.logic.domain.ToolsRepositoryMetadataUserMember;

public class ToolsRepositoryMetadataUserMemberPermissionService extends PermissionService<ToolsRepositoryMetadataUserMember, Integer> {

    public ToolsRepositoryMetadataUserMemberPermissionService(boolean allowGetAccesses, boolean allowAdminAccesses, boolean allowNonExistentEntityAccess) {
        super(allowGetAccesses, allowAdminAccesses, allowNonExistentEntityAccess);
    }



    @Override
    protected boolean isValid(ToolsRepositoryMetadataUserMember entity, User user, Access<Integer> access) throws ServiceException {
        return entity.getRepository().getOwner().getUserName().equals(access.userName);
    }

}
