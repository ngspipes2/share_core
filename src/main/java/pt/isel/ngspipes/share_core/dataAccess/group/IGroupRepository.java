package pt.isel.ngspipes.share_core.dataAccess.group;

import pt.isel.ngspipes.share_core.dataAccess.IRepository;
import pt.isel.ngspipes.share_core.dataAccess.RepositoryException;
import pt.isel.ngspipes.share_core.logic.domain.Group;

import java.util.Collection;

public interface IGroupRepository extends IRepository<Group, String> {

    Collection<Group> getGroupsOfUser(String userName) throws RepositoryException;

    Collection<Group> getGroupsWithMember(String userName) throws RepositoryException;

}
