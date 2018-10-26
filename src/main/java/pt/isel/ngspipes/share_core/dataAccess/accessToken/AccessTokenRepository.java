package pt.isel.ngspipes.share_core.dataAccess.accessToken;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import pt.isel.ngspipes.share_core.dataAccess.PostgresRepository;
import pt.isel.ngspipes.share_core.dataAccess.RepositoryException;
import pt.isel.ngspipes.share_core.logic.domain.AccessToken;

import java.util.Collection;
import java.util.stream.Collectors;

@Repository
public class AccessTokenRepository extends PostgresRepository<AccessToken, Integer> implements IAccessTokenRepository {

    public AccessTokenRepository() {
        super(AccessToken.class);
    }



    @Override
    protected void setId(AccessToken token, Integer id) {
        token.setId(id);
    }

    @Override
    protected Integer getId(AccessToken token) {
        return token.getId();
    }

    @Override
    protected void merge(AccessToken token, AccessToken tokenToUpdate) {
        tokenToUpdate.setId(token.getId());
        tokenToUpdate.setOwner(token.getOwner());
        tokenToUpdate.setToken(token.getToken());
        tokenToUpdate.setCreationDate(token.getCreationDate());
        tokenToUpdate.setName(token.getName());
        tokenToUpdate.setDescription(token.getDescription());
        tokenToUpdate.setWriteAccess(token.getWriteAccess());
    }

    @Override
    public Collection<AccessToken> getTokensOfUser(String userName) throws RepositoryException {
        return getAll().stream().filter((token) -> token.getOwner().getUserName().equals(userName)).collect(Collectors.toList());
    }

    @Override
    public void deleteTokensOfUser(String userName) throws RepositoryException {
        super.hibernateTemplate.deleteAll(getTokensOfUser(userName));
    }

}
