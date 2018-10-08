package pt.isel.ngspipes.share_core.logic.service.accessToken;

import pt.isel.ngspipes.share_core.logic.domain.AccessToken;
import pt.isel.ngspipes.share_core.logic.service.IService;
import pt.isel.ngspipes.share_core.logic.service.exceptions.ServiceException;

import java.util.Collection;

public interface IAccessTokenService extends IService<AccessToken, Integer> {

    Collection<AccessToken> getTokensOfUser(String userName) throws ServiceException;
    AccessToken getTokensByToken(String token) throws ServiceException;

}
