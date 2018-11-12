package pt.isel.ngspipes.share_core.dataAccess.repositoryUserMember;

import pt.isel.ngspipes.share_core.dataAccess.PostgresRepository;
import pt.isel.ngspipes.share_core.dataAccess.RepositoryException;
import pt.isel.ngspipes.share_core.logic.domain.RepositoryUserMember;

import java.util.Collection;
import java.util.stream.Collectors;

@org.springframework.stereotype.Repository
public class RepositoryUserMemberRepository extends PostgresRepository<RepositoryUserMember, Integer> implements IRepositoryUserMemberRepository {

    public RepositoryUserMemberRepository() {
        super(RepositoryUserMember.class);
    }



    @Override
    protected void setId(RepositoryUserMember member, Integer id) {
        member.setId(id);
    }

    @Override
    protected Integer getId(RepositoryUserMember member) {
        return member.getId();
    }

    @Override
    protected void merge(RepositoryUserMember member, RepositoryUserMember memberToUpdate) {
        memberToUpdate.setId(member.getId());
        memberToUpdate.setCreationDate(member.getCreationDate());
        memberToUpdate.setUser(member.getUser());
        memberToUpdate.setRepository(member.getRepository());
        memberToUpdate.setWriteAccess(member.getWriteAccess());
    }

    @Override
    public Collection<RepositoryUserMember> getMembersOfRepository(String repositoryName) throws RepositoryException {
        return getAll().stream().filter((member) -> member.getRepository().getRepositoryName().equals(repositoryName)).collect(Collectors.toList());
    }

    @Override
    public Collection<RepositoryUserMember> getMembersWithUser(String userName) throws RepositoryException {
        return getAll().stream().filter((member) -> member.getUser().getUserName().equals(userName)).collect(Collectors.toList());
    }

    @Override
    public void deleteMembersOfRepository(String repositoryName) throws RepositoryException {
        super.hibernateTemplate.deleteAll(getMembersOfRepository(repositoryName));
    }

    @Override
    public void deleteMembersWithUser(String userName) throws RepositoryException {
        super.hibernateTemplate.deleteAll(getMembersWithUser(userName));
    }

}
