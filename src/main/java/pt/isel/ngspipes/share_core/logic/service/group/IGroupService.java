package pt.isel.ngspipes.share_core.logic.service.group;

import pt.isel.ngspipes.share_core.logic.domain.Group;
import pt.isel.ngspipes.share_core.logic.service.IService;
import pt.isel.ngspipes.share_core.logic.service.exceptions.ServiceException;

import java.util.Collection;

public interface IGroupService extends IService<Group, String> {

    Collection<Group> getGroupsOfUser(String userName) throws ServiceException;

    Collection<Group> getGroupsWithMember(String userName) throws ServiceException;

}