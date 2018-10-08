package pt.isel.ngspipes.share_core.dataAccess.user;

import pt.isel.ngspipes.share_core.dataAccess.IRepository;
import pt.isel.ngspipes.share_core.dataAccess.RepositoryException;
import pt.isel.ngspipes.share_core.logic.domain.User;

import java.util.Collection;

public interface IUserRepository extends IRepository<User, String> {

    Collection<String> getUsersNames() throws RepositoryException;

}
