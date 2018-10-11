package pt.isel.ngspipes.share_core.dataAccess.pipelinesRepositoryMetadataUserMember;

import pt.isel.ngspipes.share_core.dataAccess.PostgresRepository;
import pt.isel.ngspipes.share_core.dataAccess.RepositoryException;
import pt.isel.ngspipes.share_core.logic.domain.PipelinesRepositoryMetadataUserMember;

import java.util.Collection;
import java.util.stream.Collectors;

@org.springframework.stereotype.Repository
public class PipelinesRepositoryMetadataUserMemberRepository extends PostgresRepository<PipelinesRepositoryMetadataUserMember, Integer> implements IPipelinesRepositoryMetadataUserMemberRepository {

    public PipelinesRepositoryMetadataUserMemberRepository() {
        super(PipelinesRepositoryMetadataUserMember.class);
    }



    @Override
    protected void setId(PipelinesRepositoryMetadataUserMember member, Integer id) {
        member.setId(id);
    }

    @Override
    protected Integer getId(PipelinesRepositoryMetadataUserMember member) {
        return member.getId();
    }

    @Override
    protected void merge(PipelinesRepositoryMetadataUserMember member, PipelinesRepositoryMetadataUserMember memberToUpdate) {
        memberToUpdate.setId(member.getId());
        memberToUpdate.setCreationDate(member.getCreationDate());
        memberToUpdate.setUser(member.getUser());
        memberToUpdate.setRepository(member.getRepository());
        memberToUpdate.setWriteAccess(member.getWriteAccess());
    }

    @Override
    public Collection<PipelinesRepositoryMetadataUserMember> getMembersOfRepository(int repositoryId) throws RepositoryException {
        return getAll().stream().filter((member) -> member.getRepository().getId() == repositoryId).collect(Collectors.toList());
    }

    @Override
    public Collection<PipelinesRepositoryMetadataUserMember> getMembersWithUser(String userName) throws RepositoryException {
        return getAll().stream().filter((member) -> member.getUser().getUserName().equals(userName)).collect(Collectors.toList());
    }
}
