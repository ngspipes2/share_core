package pt.isel.ngspipes.share_core.dataAccess.groupMember;

import org.springframework.stereotype.Repository;
import pt.isel.ngspipes.share_core.dataAccess.PostgresRepository;
import pt.isel.ngspipes.share_core.dataAccess.RepositoryException;
import pt.isel.ngspipes.share_core.logic.domain.GroupMember;

import java.util.Collection;
import java.util.stream.Collectors;

@Repository
public class GroupMemberRepository extends PostgresRepository<GroupMember, Integer> implements IGroupMemberRepository {

    public GroupMemberRepository() {
        super(GroupMember.class);
    }



    @Override
    protected void setId(GroupMember member, Integer id) {
        member.setId(id);
    }

    @Override
    protected Integer getId(GroupMember member) {
        return member.getId();
    }

    @Override
    protected void merge(GroupMember member, GroupMember memberToUpdate) {
        memberToUpdate.setId(member.getId());
        memberToUpdate.setCreationDate(member.getCreationDate());
        memberToUpdate.setUser(member.getUser());
        memberToUpdate.setGroup(member.getGroup());
        memberToUpdate.setWriteAccess(member.getWriteAccess());
    }

    @Override
    public Collection<GroupMember> getMembersWithUser(String userName) throws RepositoryException {
        return getAll().stream().filter((member) -> member.getUser().getUserName().equals(userName)).collect(Collectors.toList());
    }

    @Override
    public Collection<GroupMember> getMembersOfGroup(String groupName) throws RepositoryException {
        return getAll().stream().filter((member) -> member.getGroup().getGroupName().equals(groupName)).collect(Collectors.toList());
    }

    @Override
    public void deleteMembersWithUser(String userName) throws RepositoryException {
        super.hibernateTemplate.deleteAll(getMembersWithUser(userName));
    }

    @Override
    public void deleteMembersOfGroup(String groupName) throws RepositoryException {
        super.hibernateTemplate.deleteAll(getMembersOfGroup(groupName));
    }

}
