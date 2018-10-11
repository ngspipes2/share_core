package pt.isel.ngspipes.share_core.logic.service.permission;

import pt.isel.ngspipes.share_core.logic.domain.User;
import pt.isel.ngspipes.share_core.logic.service.exceptions.ServiceException;
import pt.isel.ngspipes.share_core.logic.service.permission.Access;
import pt.isel.ngspipes.share_core.logic.service.permission.PermissionService;
import pt.isel.ngspipes.share_core.logic.domain.ToolsRepositoryMetadataGroupMember;

public class ToolsRepositoryMetadataGroupMemberPermissionService extends PermissionService<ToolsRepositoryMetadataGroupMember, Integer> {

    public ToolsRepositoryMetadataGroupMemberPermissionService(boolean allowGetAccesses, boolean allowAdminAccesses, boolean allowNonExistentEntityAccess) {
        super(allowGetAccesses, allowAdminAccesses, allowNonExistentEntityAccess);
    }



    @Override
    protected boolean isValid(ToolsRepositoryMetadataGroupMember entity, User user, Access<Integer> access) throws ServiceException {
        return entity.getRepository().getOwner().getUserName().equals(access.userName);
    }

}
