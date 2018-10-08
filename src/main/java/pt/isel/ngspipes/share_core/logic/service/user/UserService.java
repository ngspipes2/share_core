package pt.isel.ngspipes.share_core.logic.service.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import pt.isel.ngspipes.share_core.dataAccess.RepositoryException;
import pt.isel.ngspipes.share_core.dataAccess.user.IUserRepository;
import pt.isel.ngspipes.share_core.logic.domain.User;
import pt.isel.ngspipes.share_core.logic.service.Service;
import pt.isel.ngspipes.share_core.logic.service.ServiceUtils;
import pt.isel.ngspipes.share_core.logic.service.exceptions.ServiceException;

import java.util.Collection;
import java.util.Date;

@org.springframework.stereotype.Service
public class UserService extends Service<User, String> implements IUserService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private IUserRepository repository;



    public UserService() {
        super("Users", "User");
    }



    @Override
    @Transactional
    public void insert(User user) throws ServiceException {
        user.setCreationDate(new Date());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        super.insert(user);
    }

    @Override
    @Transactional
    public void update(User user) throws ServiceException {
        User savedUser = getById(user.getUserName());

        if(savedUser != null) {
            if(!passwordEncoder.matches(user.getPassword(), savedUser.getPassword()))
                throw new ServiceException("User's password must be changed through changePassword operation!");

            user.setPassword(savedUser.getPassword());
        }

        super.update(user);
    }

    @Override
    protected void validateInsert(User user) throws ServiceException { }

    @Override
    protected void validateDelete(String userName) throws ServiceException { }

    @Override
    protected void validateUpdate(User user) throws ServiceException {
        User savedUser = getById(user.getUserName());

        if(savedUser != null) {
            if(!ServiceUtils.sameDate(savedUser.getCreationDate(), user.getCreationDate()))
                throw new ServiceException("User's creationDate cannot be changed!");

            if(!savedUser.getRole().equals(user.getRole()))
                throw new ServiceException("User's role cannot be changed!");
        }
    }

    @Override
    protected String getId(User user) throws ServiceException {
        return user.getUserName();
    }

    @Override
    public void changePassword(String userName, String currentPassword, String newPassword) throws ServiceException {
        User user = getById(userName);

        if(user == null)
            throw new ServiceException("There is no user with userName '" + userName + "'!");

        if(!passwordEncoder.matches(currentPassword, user.getPassword()))
            throw new ServiceException("Invalid currentPassword!");

        user.setPassword(passwordEncoder.encode(newPassword));

        try {
            super.repository.update(user);
        } catch (RepositoryException e) {
            throw new ServiceException("Error changing password!", e);
        }
    }

    @Override
    public Collection<String> getUsersNames() throws ServiceException {
        try {
            return repository.getUsersNames();
        } catch (RepositoryException e) {
            throw new ServiceException("Error getting users names!", e);
        }
    }

}
