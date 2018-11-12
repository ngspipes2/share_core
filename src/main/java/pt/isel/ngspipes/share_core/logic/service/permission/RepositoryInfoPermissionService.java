package pt.isel.ngspipes.share_core.logic.service.permission;

import org.springframework.stereotype.Service;
import pt.isel.ngspipes.share_core.logic.domain.RepositoryInfo;
import pt.isel.ngspipes.share_core.logic.domain.User;
import pt.isel.ngspipes.share_core.logic.service.exceptions.ServiceException;

@Service
public class RepositoryInfoPermissionService extends PermissionService<RepositoryInfo, String> {

    public RepositoryInfoPermissionService() {
        super(true, true, true);
    }



    @Override
    protected boolean isValid(RepositoryInfo entity, User user, Access<String> access) throws ServiceException {
        return entity.getOwner().getUserName().equals(access.userName);
    }

}
