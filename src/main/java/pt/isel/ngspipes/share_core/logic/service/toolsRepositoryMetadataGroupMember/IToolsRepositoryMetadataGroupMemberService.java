package pt.isel.ngspipes.share_core.logic.service.toolsRepositoryMetadataGroupMember;

import pt.isel.ngspipes.share_core.logic.service.IService;
import pt.isel.ngspipes.share_core.logic.service.exceptions.ServiceException;
import pt.isel.ngspipes.share_core.logic.domain.ToolsRepositoryMetadataGroupMember;

import java.util.Collection;

public interface IToolsRepositoryMetadataGroupMemberService extends IService<ToolsRepositoryMetadataGroupMember, Integer> {

    Collection<ToolsRepositoryMetadataGroupMember> getMembersWithGroup(String groupName) throws ServiceException;

    Collection<ToolsRepositoryMetadataGroupMember> getMembersOfRepository(int repositoryId) throws ServiceException;

}
