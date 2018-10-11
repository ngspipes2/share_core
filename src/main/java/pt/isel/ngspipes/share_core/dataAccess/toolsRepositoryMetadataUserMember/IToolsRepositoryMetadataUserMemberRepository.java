package pt.isel.ngspipes.share_core.dataAccess.toolsRepositoryMetadataUserMember;

import pt.isel.ngspipes.share_core.dataAccess.IRepository;
import pt.isel.ngspipes.share_core.dataAccess.RepositoryException;
import pt.isel.ngspipes.share_core.logic.domain.ToolsRepositoryMetadataUserMember;

import java.util.Collection;

public interface IToolsRepositoryMetadataUserMemberRepository extends IRepository<ToolsRepositoryMetadataUserMember, Integer> {

    Collection<ToolsRepositoryMetadataUserMember> getMembersOfRepository(int repositoryId) throws RepositoryException;

    Collection<ToolsRepositoryMetadataUserMember> getMembersWithUser(String userName) throws RepositoryException;

}
