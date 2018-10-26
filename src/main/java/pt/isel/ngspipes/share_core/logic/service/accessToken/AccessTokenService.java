package pt.isel.ngspipes.share_core.logic.service.accessToken;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import pt.isel.ngspipes.share_core.dataAccess.RepositoryException;
import pt.isel.ngspipes.share_core.dataAccess.accessToken.IAccessTokenRepository;
import pt.isel.ngspipes.share_core.logic.domain.AccessToken;
import pt.isel.ngspipes.share_core.logic.service.ICurrentUserSupplier;
import pt.isel.ngspipes.share_core.logic.service.Service;
import pt.isel.ngspipes.share_core.logic.service.ServiceUtils;
import pt.isel.ngspipes.share_core.logic.service.exceptions.ServiceException;

import java.util.Collection;
import java.util.Date;
import java.util.UUID;

@org.springframework.stereotype.Service
public class AccessTokenService extends Service<AccessToken, Integer> implements IAccessTokenService {

    @Autowired
    private IAccessTokenRepository repository;
    @Autowired
    private ICurrentUserSupplier currentUserSupplier;
    @Autowired
    private PasswordEncoder passwordEncoder;



    public AccessTokenService() {
        super("AccessTokens", "AccessToken");
    }



    @Override
    @Transactional
    public void insert(AccessToken token) throws ServiceException {
        String tokenStr = UUID.randomUUID().toString();

        token.setToken(passwordEncoder.encode(tokenStr));
        token.setOwner(currentUserSupplier.get());
        token.setCreationDate(new Date());

        super.insert(token);

        token.setToken(tokenStr);
    }

    @Override
    @Transactional
    public void update(AccessToken token) throws ServiceException {
        AccessToken savedToken = getById(token.getId());

        if(savedToken != null)
            token.setToken(savedToken.getToken());

        super.update(token);
    }

    @Override
    protected void validateInsert(AccessToken token) throws ServiceException { }

    @Override
    protected void validateDelete(Integer id) throws ServiceException { }

    @Override
    protected void validateUpdate(AccessToken token) throws ServiceException {
        AccessToken savedToken = getById(token.getId());

        if(savedToken != null) {
            if(!token.getOwner().getUserName().equals(savedToken.getOwner().getUserName()))
                throw new ServiceException("Token's owner cannot be changed!");

            if(!ServiceUtils.sameDate(token.getCreationDate(), savedToken.getCreationDate()))
                throw new ServiceException("Token's creationDate cannot be changed!");
        }
    }

    @Override
    protected Integer getId(AccessToken token) throws ServiceException {
        return token.getId();
    }

    @Override
    public Collection<AccessToken> getTokensOfUser(String userName) throws ServiceException {
        try{
            return repository.getTokensOfUser(userName);
        } catch (RepositoryException e) {
            throw new ServiceException("Error getting tokens of User!", e);
        }
    }

    @Override
    public AccessToken getAccessTokenByToken(String token) throws ServiceException {
        try {
            return repository.getByToken(passwordEncoder.encode(token));
        } catch (RepositoryException e) {
            throw new ServiceException("Error getting token by token!", e);
        }
    }

    @Override
    @Transactional
    public void deleteTokensOfUser(String userName) throws ServiceException {
        try {
            repository.deleteTokensOfUser(userName);
        } catch (RepositoryException e) {
            throw new ServiceException("Error deleting tokens of user:" + userName + "!", e);
        }
    }

}
