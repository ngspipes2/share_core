package pt.isel.ngspipes.share_core.dataAccess.group;

import org.springframework.stereotype.Repository;
import pt.isel.ngspipes.share_core.dataAccess.PostgresRepository;
import pt.isel.ngspipes.share_core.dataAccess.RepositoryException;
import pt.isel.ngspipes.share_core.logic.domain.Group;
import pt.isel.ngspipes.share_core.logic.domain.User;

import java.util.Collection;
import java.util.stream.Collectors;

@Repository
public class GroupRepository extends PostgresRepository<Group, String> implements IGroupRepository {

    public GroupRepository() {
        super(Group.class);
    }



    @Override
    protected void setId(Group group, String groupName) {
        group.setGroupName(groupName);
    }

    @Override
    protected String getId(Group group) {
        return group.getGroupName();
    }

    @Override
    protected void merge(Group group, Group groupToUpdate) {
        groupToUpdate.setGroupName(group.getGroupName());
        groupToUpdate.setDescription(group.getDescription());
        groupToUpdate.setOwner(group.getOwner());
        groupToUpdate.setCreationDate(group.getCreationDate());
        groupToUpdate.setMembers(group.getMembers());
    }

    @Override
    public Collection<Group> getGroupsOfUser(String userName) throws RepositoryException {
        return getAll().stream().filter((group) -> group.getOwner().getUserName().equals(userName)).collect(Collectors.toList());
    }

    @Override
    public Collection<Group> getGroupsWithMember(String userName) throws RepositoryException {
        return getAll().stream().filter((group) -> {
            Collection<User> users = group.getMembers();
            return users.stream().anyMatch((user)->user.getUserName().equals(userName));
        }).collect(Collectors.toList());
    }

}
