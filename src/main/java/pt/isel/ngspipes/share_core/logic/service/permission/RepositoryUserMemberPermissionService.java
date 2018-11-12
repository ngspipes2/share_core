package pt.isel.ngspipes.share_core.logic.service.permission;

import org.springframework.stereotype.Service;
import pt.isel.ngspipes.share_core.logic.domain.RepositoryUserMember;
import pt.isel.ngspipes.share_core.logic.domain.User;
import pt.isel.ngspipes.share_core.logic.service.exceptions.ServiceException;

@Service
public class RepositoryUserMemberPermissionService extends PermissionService<RepositoryUserMember, Integer> {

    public RepositoryUserMemberPermissionService() {
        super(true, true, true);
    }



    @Override
    protected boolean isValid(RepositoryUserMember entity, User user, Access<Integer> access) throws ServiceException {
        return entity.getRepository().getOwner().getUserName().equals(access.userName);
    }

}
