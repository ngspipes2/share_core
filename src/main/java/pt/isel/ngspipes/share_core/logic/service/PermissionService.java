package pt.isel.ngspipes.share_core.logic.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pt.isel.ngspipes.share_core.logic.domain.Group;
import pt.isel.ngspipes.share_core.logic.domain.ToolsRepository;
import pt.isel.ngspipes.share_core.logic.domain.User;
import pt.isel.ngspipes.share_core.logic.service.exceptions.ServiceException;
import pt.isel.ngspipes.share_core.logic.service.group.IGroupService;
import pt.isel.ngspipes.share_core.logic.service.toolsRepository.IToolsRepositoryService;

@Service
public class PermissionService {

    public static class Access {

        public enum Operation {
            GET, INSERT, UPDATE, DELETE
        }



        public String userName;
        public Operation operation;
        public Class<?> entity;
        public String entityId;



        public Access(String userName, Operation operation, Class<?> entity, String entityId) {
            this.userName = userName;
            this.operation = operation;
            this.entity = entity;
            this.entityId = entityId;
        }

        public Access() { }

    }



    @Autowired
    private IService<User, String> userService;

    @Autowired
    private IGroupService groupService;

    @Autowired
    private IToolsRepositoryService toolsRepositoryService;



    @Transactional
    public boolean isValid(Access access) throws ServiceException {
        if(access.operation.equals(Access.Operation.GET))
            return true;

        User user = access.userName == null ? null : userService.getById(access.userName);
        if(user != null && user.getRole().equals(User.Role.ADMIN))
            return true;

        switch (access.entity.getSimpleName()) {
            case "User": return isValidUserAccess(access);
            case "Group": return isValidGroupAccess(access);
            case "ToolsRepository": return isValidToolsRepositoryAccess(access);
            default: throw new ServiceException("Unknown entity " + access.entity.getSimpleName() + "!");
        }
    }

    private boolean isValidUserAccess(Access access) throws ServiceException {
        User user = access.entityId == null ? null : userService.getById(access.entityId);

        if(user == null)//accessing non existent User
            return true;

        return user.getUserName().equals(access.userName);
    }

    private boolean isValidGroupAccess(Access access) throws ServiceException {
        Group group = access.entityId == null ? null : groupService.getById(access.entityId);

        if(group == null)//accessing non existent Group
            return true;

        return group.getOwner().getUserName().equals(access.userName);
    }

    private boolean isValidToolsRepositoryAccess(Access access) throws ServiceException {
        ToolsRepository repository = access.entityId == null ? null : toolsRepositoryService.getById(Integer.parseInt(access.entityId));

        if(repository == null)//accessing non existent ToolsRepository
            return true;

        return repository.getOwner().getUserName().equals(access.userName);
    }
}
