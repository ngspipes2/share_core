package pt.isel.ngspipes.share_core.dataAccess.repositoryGroupMember;

import pt.isel.ngspipes.share_core.dataAccess.IRepository;
import pt.isel.ngspipes.share_core.dataAccess.RepositoryException;
import pt.isel.ngspipes.share_core.logic.domain.RepositoryGroupMember;

import java.util.Collection;

public interface IRepositoryGroupMemberRepository extends IRepository<RepositoryGroupMember, Integer> {

    Collection<RepositoryGroupMember> getMembersOfRepository(String repositoryName) throws RepositoryException;

    Collection<RepositoryGroupMember> getMembersWithGroup(String groupName) throws RepositoryException;

    void deleteMembersOfRepository(String repositoryName) throws RepositoryException;

    void deleteMembersWithGroup(String groupName) throws RepositoryException;

}
