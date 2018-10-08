package pt.isel.ngspipes.share_core.dataAccess.groupMember;

import pt.isel.ngspipes.share_core.dataAccess.IRepository;
import pt.isel.ngspipes.share_core.dataAccess.RepositoryException;
import pt.isel.ngspipes.share_core.logic.domain.GroupMember;

import java.util.Collection;

public interface IGroupMemberRepository extends IRepository<GroupMember, Integer> {

    Collection<GroupMember> getMembersWithUser(String userName) throws RepositoryException;

    Collection<GroupMember> getMembersOfGroup(String groupName) throws RepositoryException;

}
