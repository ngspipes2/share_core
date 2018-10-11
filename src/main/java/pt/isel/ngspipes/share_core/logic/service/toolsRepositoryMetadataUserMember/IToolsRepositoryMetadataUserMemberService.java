package pt.isel.ngspipes.share_core.logic.service.toolsRepositoryMetadataUserMember;

import pt.isel.ngspipes.share_core.logic.service.IService;
import pt.isel.ngspipes.share_core.logic.service.exceptions.ServiceException;
import pt.isel.ngspipes.share_core.logic.domain.ToolsRepositoryMetadataUserMember;

import java.util.Collection;

public interface IToolsRepositoryMetadataUserMemberService extends IService<ToolsRepositoryMetadataUserMember, Integer> {

    Collection<ToolsRepositoryMetadataUserMember> getMembersWithUser(String userName) throws ServiceException;

    Collection<ToolsRepositoryMetadataUserMember> getMembersOfRepository(int repositoryId) throws ServiceException;

}
