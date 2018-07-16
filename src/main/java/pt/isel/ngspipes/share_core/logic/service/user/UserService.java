package pt.isel.ngspipes.share_core.logic.service.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import pt.isel.ngspipes.share_core.logic.domain.User;
import pt.isel.ngspipes.share_core.logic.service.Service;
import pt.isel.ngspipes.share_core.logic.service.ServiceUtils;
import pt.isel.ngspipes.share_core.logic.service.exceptions.ServiceException;

import java.util.Date;

@org.springframework.stereotype.Service
public class UserService extends Service<User, String> {

    @Autowired
    private PasswordEncoder passwordEncoder;



    protected UserService() {
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
        user.setPassword(passwordEncoder.encode(user.getPassword()));
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
            if(!ServiceUtils.sameCreationDate(savedUser.getCreationDate(), user.getCreationDate()))
                throw new ServiceException("User's creationDate cannot be changed!");

            if(!savedUser.getRole().equals(user.getRole()))
                throw new ServiceException("User's role cannot be changed!");
        }
    }

    @Override
    protected String getId(User user) throws ServiceException {
        return user.getUserName();
    }

}
