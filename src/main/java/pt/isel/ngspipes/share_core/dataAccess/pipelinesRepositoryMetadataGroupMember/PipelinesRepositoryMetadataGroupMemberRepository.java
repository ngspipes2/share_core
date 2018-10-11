package pt.isel.ngspipes.share_core.dataAccess.pipelinesRepositoryMetadataGroupMember;

import pt.isel.ngspipes.share_core.dataAccess.PostgresRepository;
import pt.isel.ngspipes.share_core.dataAccess.RepositoryException;
import pt.isel.ngspipes.share_core.logic.domain.PipelinesRepositoryMetadataGroupMember;

import java.util.Collection;
import java.util.stream.Collectors;

@org.springframework.stereotype.Repository
public class PipelinesRepositoryMetadataGroupMemberRepository extends PostgresRepository<PipelinesRepositoryMetadataGroupMember, Integer> implements IPipelinesRepositoryMetadataGroupMemberRepository {

    public PipelinesRepositoryMetadataGroupMemberRepository() {
        super(PipelinesRepositoryMetadataGroupMember.class);
    }



    @Override
    protected void setId(PipelinesRepositoryMetadataGroupMember member, Integer id) {
        member.setId(id);
    }

    @Override
    protected Integer getId(PipelinesRepositoryMetadataGroupMember member) {
        return member.getId();
    }

    @Override
    protected void merge(PipelinesRepositoryMetadataGroupMember member, PipelinesRepositoryMetadataGroupMember memberToUpdate) {
        memberToUpdate.setId(member.getId());
        memberToUpdate.setCreationDate(member.getCreationDate());
        memberToUpdate.setGroup(member.getGroup());
        memberToUpdate.setRepository(member.getRepository());
        memberToUpdate.setWriteAccess(member.getWriteAccess());
    }

    @Override
    public Collection<PipelinesRepositoryMetadataGroupMember> getMembersOfRepository(int repositoryId) throws RepositoryException {
        return getAll().stream().filter((member) -> member.getRepository().getId() == repositoryId).collect(Collectors.toList());
    }

    @Override
    public Collection<PipelinesRepositoryMetadataGroupMember> getMembersWithGroup(String groupName) throws RepositoryException {
        return getAll().stream().filter((member) -> member.getGroup().getGroupName().equals(groupName)).collect(Collectors.toList());
    }
}
