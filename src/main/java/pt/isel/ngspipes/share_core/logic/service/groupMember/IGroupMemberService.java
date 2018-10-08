package pt.isel.ngspipes.share_core.logic.service.groupMember;


import pt.isel.ngspipes.share_core.logic.domain.GroupMember;
import pt.isel.ngspipes.share_core.logic.service.IService;
import pt.isel.ngspipes.share_core.logic.service.exceptions.ServiceException;

import java.util.Collection;

public interface IGroupMemberService extends IService<GroupMember, Integer> {

    Collection<GroupMember> getMembersWithUser(String userName) throws ServiceException;

    Collection<GroupMember> getMembersOfGroup(String groupName) throws ServiceException;

}
