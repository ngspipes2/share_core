package pt.isel.ngspipes.share_core.dataAccess.user;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.springframework.stereotype.Repository;
import pt.isel.ngspipes.share_core.dataAccess.PostgresRepository;
import pt.isel.ngspipes.share_core.logic.domain.User;

import java.util.Collection;

@Repository
public class UserRepository extends PostgresRepository<User, String>  implements IUserRepository {

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

    @Override
    public Collection<String> getUsersNames() {
        Session session = this.hibernateTemplate.getSessionFactory().getCurrentSession();
        Criteria criteria = session.createCriteria(User.class);
        criteria.setProjection(Projections.projectionList().add(Projections.property("userName")));

        return criteria.list();
    }

}
