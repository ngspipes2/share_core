package pt.isel.ngspipes.share_core.dataAccess.repositoryUserMember;

import pt.isel.ngspipes.share_core.dataAccess.IRepository;
import pt.isel.ngspipes.share_core.dataAccess.RepositoryException;
import pt.isel.ngspipes.share_core.logic.domain.RepositoryUserMember;

import java.util.Collection;

public interface IRepositoryUserMemberRepository extends IRepository<RepositoryUserMember, Integer> {

    Collection<RepositoryUserMember> getMembersOfRepository(String repositoryName) throws RepositoryException;

    Collection<RepositoryUserMember> getMembersWithUser(String userName) throws RepositoryException;

    void deleteMembersOfRepository(String repositoryName) throws RepositoryException;

    void deleteMembersWithUser(String userName) throws RepositoryException;

}
