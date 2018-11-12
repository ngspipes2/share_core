package pt.isel.ngspipes.share_core.logic.service.permission;

import org.springframework.stereotype.Service;
import pt.isel.ngspipes.share_core.logic.domain.RepositoryGroupMember;
import pt.isel.ngspipes.share_core.logic.domain.User;
import pt.isel.ngspipes.share_core.logic.service.exceptions.ServiceException;

@Service
public class RepositoryGroupMemberPermissionService extends PermissionService<RepositoryGroupMember, Integer> {

    public RepositoryGroupMemberPermissionService() {
        super(true, true, true);
    }



    @Override
    protected boolean isValid(RepositoryGroupMember entity, User user, Access<Integer> access) throws ServiceException {
        return entity.getRepository().getOwner().getUserName().equals(access.userName);
    }

}
