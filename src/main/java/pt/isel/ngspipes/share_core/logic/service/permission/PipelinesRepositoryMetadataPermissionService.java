package pt.isel.ngspipes.share_core.logic.service.permission;

import pt.isel.ngspipes.share_core.logic.domain.PipelinesRepositoryMetadata;
import pt.isel.ngspipes.share_core.logic.domain.User;
import pt.isel.ngspipes.share_core.logic.service.exceptions.ServiceException;

public class PipelinesRepositoryMetadataPermissionService extends PermissionService<PipelinesRepositoryMetadata, Integer> {

    public PipelinesRepositoryMetadataPermissionService(boolean allowGetAccesses, boolean allowAdminAccesses, boolean allowNonExistentEntityAccess) {
        super(allowGetAccesses, allowAdminAccesses, allowNonExistentEntityAccess);
    }



    @Override
    protected boolean isValid(PipelinesRepositoryMetadata entity, User user, Access<Integer> access) throws ServiceException {
        return entity.getOwner().getUserName().equals(access.userName);
    }

}
