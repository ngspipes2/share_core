package pt.isel.ngspipes.share_core.dataAccess.toolsRepositoryMetadataGroupMember;

import pt.isel.ngspipes.share_core.dataAccess.IRepository;
import pt.isel.ngspipes.share_core.dataAccess.RepositoryException;
import pt.isel.ngspipes.share_core.logic.domain.ToolsRepositoryMetadataGroupMember;

import java.util.Collection;

public interface IToolsRepositoryMetadataGroupMemberRepository extends IRepository<ToolsRepositoryMetadataGroupMember, Integer> {

    Collection<ToolsRepositoryMetadataGroupMember> getMembersOfRepository(int repositoryId) throws RepositoryException;

    Collection<ToolsRepositoryMetadataGroupMember> getMembersWithGroup(String groupName) throws RepositoryException;

}
