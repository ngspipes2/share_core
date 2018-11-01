package pt.isel.ngspipes.share_core.logic.service.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import pt.isel.ngspipes.share_core.dataAccess.RepositoryException;
import pt.isel.ngspipes.share_core.dataAccess.user.IUserRepository;
import pt.isel.ngspipes.share_core.logic.domain.Image;
import pt.isel.ngspipes.share_core.logic.domain.User;
import pt.isel.ngspipes.share_core.logic.service.IService;
import pt.isel.ngspipes.share_core.logic.service.Service;
import pt.isel.ngspipes.share_core.logic.service.ServiceUtils;
import pt.isel.ngspipes.share_core.logic.service.exceptions.NonExistentEntityException;
import pt.isel.ngspipes.share_core.logic.service.exceptions.ServiceException;

import java.util.Collection;
import java.util.Date;

@org.springframework.stereotype.Service
public class UserService extends Service<User, String> implements IUserService {

    private static final String IMAGE_PREFIX = "User";



    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private IUserRepository repository;
    @Autowired
    private IService<Image, String> imageService;



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
    public void delete(String userName) throws ServiceException {
        super.delete(userName);

        if(imageService.getById(IMAGE_PREFIX + userName) != null)
            imageService.delete(IMAGE_PREFIX + userName);
    }

    @Override
    @Transactional
    public void update(User user) throws ServiceException {
        User savedUser = getById(user.getUserName());

        if(savedUser != null)
            user.setPassword(savedUser.getPassword());

        super.update(user);
    }

    @Override
    protected void validateInsert(User user) throws ServiceException {
        String acceptedCharactersRegex = "[a-zA-Z0-9\\-_]+";
        if(!user.getUserName().matches(acceptedCharactersRegex))
            throw new ServiceException("UserName can only contain these characters " + acceptedCharactersRegex);
    }

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

    @Override
    public Image getUserImage(String userName) throws ServiceException {
        if(getById(userName) == null)
            return null;

        return imageService.getById(IMAGE_PREFIX + userName);
    }

    @Override
    public void setUserImage(String userName, Image image) throws ServiceException {
        if(getById(userName) == null)
            throw new NonExistentEntityException("There is no user:" + userName + "!");

        image.setId(IMAGE_PREFIX + userName);

        if(imageService.getById(image.getId()) == null)
            imageService.insert(image);
        else
            imageService.update(image);
    }

}
