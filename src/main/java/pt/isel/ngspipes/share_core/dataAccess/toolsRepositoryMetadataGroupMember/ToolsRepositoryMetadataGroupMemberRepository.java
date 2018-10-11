package pt.isel.ngspipes.share_core.dataAccess.toolsRepositoryMetadataGroupMember;

import pt.isel.ngspipes.share_core.dataAccess.PostgresRepository;
import pt.isel.ngspipes.share_core.dataAccess.RepositoryException;
import pt.isel.ngspipes.share_core.logic.domain.ToolsRepositoryMetadataGroupMember;

import java.util.Collection;
import java.util.stream.Collectors;

@org.springframework.stereotype.Repository
public class ToolsRepositoryMetadataGroupMemberRepository extends PostgresRepository<ToolsRepositoryMetadataGroupMember, Integer> implements IToolsRepositoryMetadataGroupMemberRepository {

    public ToolsRepositoryMetadataGroupMemberRepository() {
        super(ToolsRepositoryMetadataGroupMember.class);
    }



    @Override
    protected void setId(ToolsRepositoryMetadataGroupMember member, Integer id) {
        member.setId(id);
    }

    @Override
    protected Integer getId(ToolsRepositoryMetadataGroupMember member) {
        return member.getId();
    }

    @Override
    protected void merge(ToolsRepositoryMetadataGroupMember member, ToolsRepositoryMetadataGroupMember memberToUpdate) {
        memberToUpdate.setId(member.getId());
        memberToUpdate.setCreationDate(member.getCreationDate());
        memberToUpdate.setGroup(member.getGroup());
        memberToUpdate.setRepository(member.getRepository());
        memberToUpdate.setWriteAccess(member.getWriteAccess());
    }

    @Override
    public Collection<ToolsRepositoryMetadataGroupMember> getMembersOfRepository(int repositoryId) throws RepositoryException {
        return getAll().stream().filter((member) -> member.getRepository().getId() == repositoryId).collect(Collectors.toList());
    }

    @Override
    public Collection<ToolsRepositoryMetadataGroupMember> getMembersWithGroup(String groupName) throws RepositoryException {
        return getAll().stream().filter((member) -> member.getGroup().getGroupName().equals(groupName)).collect(Collectors.toList());
    }
}
