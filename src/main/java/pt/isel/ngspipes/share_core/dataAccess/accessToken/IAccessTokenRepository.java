package pt.isel.ngspipes.share_core.dataAccess.accessToken;

import pt.isel.ngspipes.share_core.dataAccess.IRepository;
import pt.isel.ngspipes.share_core.dataAccess.RepositoryException;
import pt.isel.ngspipes.share_core.logic.domain.AccessToken;

import java.util.Collection;

public interface IAccessTokenRepository extends IRepository<AccessToken, Integer> {

    Collection<AccessToken> getTokensOfUser(String userName) throws RepositoryException;
    AccessToken getByToken(String token) throws RepositoryException;
    void deleteTokensOfUser(String userName) throws RepositoryException;

}
