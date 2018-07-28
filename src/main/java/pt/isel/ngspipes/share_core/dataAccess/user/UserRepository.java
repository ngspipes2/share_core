package pt.isel.ngspipes.share_core.dataAccess.user;

import org.springframework.stereotype.Repository;
import pt.isel.ngspipes.share_core.dataAccess.PostgresRepository;
import pt.isel.ngspipes.share_core.logic.domain.User;

@Repository
public class UserRepository extends PostgresRepository<User, String> {

    public UserRepository() {
        super(User.class);
    }



    @Override
    protected void setId(User user, String userName) {
        user.setUserName(userName);
    }

    @Override
    protected String getId(User user) {
        return user.getUserName();
    }

    @Override
    protected void merge(User user, User userToUpdate) {
        userToUpdate.setUserName(user.getUserName());
        userToUpdate.setPassword(user.getPassword());
        userToUpdate.setEmail(user.getEmail());
        userToUpdate.setName(user.getName());
        userToUpdate.setCreationDate(user.getCreationDate());
        userToUpdate.setRole(user.getRole());
    }

}
