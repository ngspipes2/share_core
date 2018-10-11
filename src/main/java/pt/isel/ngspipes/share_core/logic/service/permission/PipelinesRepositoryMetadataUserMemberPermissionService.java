package pt.isel.ngspipes.share_core.logic.service.permission;

import pt.isel.ngspipes.share_core.logic.domain.PipelinesRepositoryMetadataUserMember;
import pt.isel.ngspipes.share_core.logic.domain.User;
import pt.isel.ngspipes.share_core.logic.service.exceptions.ServiceException;

public class PipelinesRepositoryMetadataUserMemberPermissionService extends PermissionService<PipelinesRepositoryMetadataUserMember, Integer> {

    public PipelinesRepositoryMetadataUserMemberPermissionService(boolean allowGetAccesses, boolean allowAdminAccesses, boolean allowNonExistentEntityAccess) {
        super(allowGetAccesses, allowAdminAccesses, allowNonExistentEntityAccess);
    }



    @Override
    protected boolean isValid(PipelinesRepositoryMetadataUserMember entity, User user, Access<Integer> access) throws ServiceException {
        return entity.getRepository().getOwner().getUserName().equals(access.userName);
    }

}
