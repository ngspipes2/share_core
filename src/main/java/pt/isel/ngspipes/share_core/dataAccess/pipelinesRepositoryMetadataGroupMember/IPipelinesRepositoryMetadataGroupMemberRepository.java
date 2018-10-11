package pt.isel.ngspipes.share_core.dataAccess.pipelinesRepositoryMetadataGroupMember;

import pt.isel.ngspipes.share_core.dataAccess.IRepository;
import pt.isel.ngspipes.share_core.dataAccess.RepositoryException;
import pt.isel.ngspipes.share_core.logic.domain.PipelinesRepositoryMetadataGroupMember;

import java.util.Collection;

public interface IPipelinesRepositoryMetadataGroupMemberRepository extends IRepository<PipelinesRepositoryMetadataGroupMember, Integer> {

    Collection<PipelinesRepositoryMetadataGroupMember> getMembersOfRepository(int repositoryId) throws RepositoryException;

    Collection<PipelinesRepositoryMetadataGroupMember> getMembersWithGroup(String groupName) throws RepositoryException;

}
