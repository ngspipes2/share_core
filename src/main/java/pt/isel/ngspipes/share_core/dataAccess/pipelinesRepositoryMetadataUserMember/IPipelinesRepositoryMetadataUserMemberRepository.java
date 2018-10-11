package pt.isel.ngspipes.share_core.dataAccess.pipelinesRepositoryMetadataUserMember;

import pt.isel.ngspipes.share_core.dataAccess.IRepository;
import pt.isel.ngspipes.share_core.dataAccess.RepositoryException;
import pt.isel.ngspipes.share_core.logic.domain.PipelinesRepositoryMetadataUserMember;

import java.util.Collection;

public interface IPipelinesRepositoryMetadataUserMemberRepository extends IRepository<PipelinesRepositoryMetadataUserMember, Integer> {

    Collection<PipelinesRepositoryMetadataUserMember> getMembersOfRepository(int repositoryId) throws RepositoryException;

    Collection<PipelinesRepositoryMetadataUserMember> getMembersWithUser(String userName) throws RepositoryException;

}
