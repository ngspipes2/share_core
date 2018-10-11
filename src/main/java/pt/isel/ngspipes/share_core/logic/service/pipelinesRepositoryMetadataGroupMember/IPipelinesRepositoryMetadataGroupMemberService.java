package pt.isel.ngspipes.share_core.logic.service.pipelinesRepositoryMetadataGroupMember;

import pt.isel.ngspipes.share_core.logic.domain.PipelinesRepositoryMetadataGroupMember;
import pt.isel.ngspipes.share_core.logic.service.IService;
import pt.isel.ngspipes.share_core.logic.service.exceptions.ServiceException;

import java.util.Collection;

public interface IPipelinesRepositoryMetadataGroupMemberService extends IService<PipelinesRepositoryMetadataGroupMember, Integer> {

    Collection<PipelinesRepositoryMetadataGroupMember> getMembersWithGroup(String groupName) throws ServiceException;

    Collection<PipelinesRepositoryMetadataGroupMember> getMembersOfRepository(int repositoryId) throws ServiceException;

}
