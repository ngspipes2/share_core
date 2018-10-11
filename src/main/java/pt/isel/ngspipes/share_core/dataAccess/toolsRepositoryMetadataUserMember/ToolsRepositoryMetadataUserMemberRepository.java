package pt.isel.ngspipes.share_core.dataAccess.toolsRepositoryMetadataUserMember;

import pt.isel.ngspipes.share_core.dataAccess.PostgresRepository;
import pt.isel.ngspipes.share_core.dataAccess.RepositoryException;
import pt.isel.ngspipes.share_core.logic.domain.ToolsRepositoryMetadataUserMember;

import java.util.Collection;
import java.util.stream.Collectors;

@org.springframework.stereotype.Repository
public class ToolsRepositoryMetadataUserMemberRepository extends PostgresRepository<ToolsRepositoryMetadataUserMember, Integer> implements IToolsRepositoryMetadataUserMemberRepository {

    public ToolsRepositoryMetadataUserMemberRepository() {
        super(ToolsRepositoryMetadataUserMember.class);
    }



    @Override
    protected void setId(ToolsRepositoryMetadataUserMember member, Integer id) {
        member.setId(id);
    }

    @Override
    protected Integer getId(ToolsRepositoryMetadataUserMember member) {
        return member.getId();
    }

    @Override
    protected void merge(ToolsRepositoryMetadataUserMember member, ToolsRepositoryMetadataUserMember memberToUpdate) {
        memberToUpdate.setId(member.getId());
        memberToUpdate.setCreationDate(member.getCreationDate());
        memberToUpdate.setUser(member.getUser());
        memberToUpdate.setRepository(member.getRepository());
        memberToUpdate.setWriteAccess(member.getWriteAccess());
    }

    @Override
    public Collection<ToolsRepositoryMetadataUserMember> getMembersOfRepository(int repositoryId) throws RepositoryException {
        return getAll().stream().filter((member) -> member.getRepository().getId() == repositoryId).collect(Collectors.toList());
    }

    @Override
    public Collection<ToolsRepositoryMetadataUserMember> getMembersWithUser(String userName) throws RepositoryException {
        return getAll().stream().filter((member) -> member.getUser().getUserName().equals(userName)).collect(Collectors.toList());
    }
}
