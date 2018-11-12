package pt.isel.ngspipes.share_core.dataAccess.repositoryGroupMember;

import pt.isel.ngspipes.share_core.dataAccess.PostgresRepository;
import pt.isel.ngspipes.share_core.dataAccess.RepositoryException;
import pt.isel.ngspipes.share_core.logic.domain.RepositoryGroupMember;

import java.util.Collection;
import java.util.stream.Collectors;

@org.springframework.stereotype.Repository
public class RepositoryGroupMemberRepository extends PostgresRepository<RepositoryGroupMember, Integer> implements IRepositoryGroupMemberRepository {

    public RepositoryGroupMemberRepository() {
        super(RepositoryGroupMember.class);
    }



    @Override
    protected void setId(RepositoryGroupMember member, Integer id) {
        member.setId(id);
    }

    @Override
    protected Integer getId(RepositoryGroupMember member) {
        return member.getId();
    }

    @Override
    protected void merge(RepositoryGroupMember member, RepositoryGroupMember memberToUpdate) {
        memberToUpdate.setId(member.getId());
        memberToUpdate.setCreationDate(member.getCreationDate());
        memberToUpdate.setGroup(member.getGroup());
        memberToUpdate.setRepository(member.getRepository());
        memberToUpdate.setWriteAccess(member.getWriteAccess());
    }

    @Override
    public Collection<RepositoryGroupMember> getMembersOfRepository(String repositoryName) throws RepositoryException {
        return getAll().stream().filter((member) -> member.getRepository().getRepositoryName().equals(repositoryName)).collect(Collectors.toList());
    }

    @Override
    public Collection<RepositoryGroupMember> getMembersWithGroup(String groupName) throws RepositoryException {
        return getAll().stream().filter((member) -> member.getGroup().getGroupName().equals(groupName)).collect(Collectors.toList());
    }

    @Override
    public void deleteMembersOfRepository(String repositoryName) throws RepositoryException {
        super.hibernateTemplate.deleteAll(getMembersOfRepository(repositoryName));
    }

    @Override
    public void deleteMembersWithGroup(String groupName) throws RepositoryException {
        super.hibernateTemplate.deleteAll(getMembersWithGroup(groupName));
    }

}
