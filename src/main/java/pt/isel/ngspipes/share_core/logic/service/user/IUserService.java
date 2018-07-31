package pt.isel.ngspipes.share_core.logic.service.user;

import pt.isel.ngspipes.share_core.logic.domain.User;
import pt.isel.ngspipes.share_core.logic.service.IService;
import pt.isel.ngspipes.share_core.logic.service.exceptions.ServiceException;

public interface IUserService extends IService<User, String> {

    void changePassword(String userName, String currentPassword, String newPassword) throws ServiceException;

}
