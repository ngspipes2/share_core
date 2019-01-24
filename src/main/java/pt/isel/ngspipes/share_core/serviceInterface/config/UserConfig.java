package pt.isel.ngspipes.share_core.serviceInterface.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;
import pt.isel.ngspipes.share_core.logic.domain.User;
import pt.isel.ngspipes.share_core.logic.service.ICurrentUserSupplier;
import pt.isel.ngspipes.share_core.logic.service.IService;
import pt.isel.ngspipes.share_core.logic.service.exceptions.ServiceException;

import java.util.Date;

@Configuration
public class UserConfig {

    @Autowired
    private IService<User, String> userService;

    @Value("${admin.username}")
    private String adminUserName;
    @Value("${admin.password}")
    private String adminPassword;



    @Bean
    @Transactional
    public boolean createAdmin() throws ServiceException {
        if(userService.getById(adminUserName) == null) {
            User user = createUser();

            userService.insert(user);

            return true;
        }

        return false;
    }

    private User createUser() {
        User user = new User();

        user.setUserName(adminUserName);
        user.setPassword(adminPassword);
        user.setRole(User.Role.ADMIN);
        user.setCreationDate(new Date());

        return user;
    }

    @Bean
    public ICurrentUserSupplier getCurrentUserSupplier() {
        return () -> {
            SecurityContext context = SecurityContextHolder.getContext();
            Authentication auth = context.getAuthentication();

            if(auth == null)
                return null;

            return userService.getById(auth.getName());
        };
    }

}
