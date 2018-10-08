package pt.isel.ngspipes.share_core.logic.service.permission;

import org.springframework.stereotype.Service;
import pt.isel.ngspipes.share_core.logic.service.exceptions.ServiceException;

@Service
public interface IPermissionService<T, K> {

    boolean isValidAccess(Access<K> access) throws ServiceException;

}
