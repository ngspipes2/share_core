package pt.isel.ngspipes.share_core.logic.service.pipelinesRepositoryMetadataUserMember;

import pt.isel.ngspipes.share_core.logic.domain.PipelinesRepositoryMetadataUserMember;
import pt.isel.ngspipes.share_core.logic.service.IService;
import pt.isel.ngspipes.share_core.logic.service.exceptions.ServiceException;

import java.util.Collection;

public interface IPipelinesRepositoryMetadataUserMemberService extends IService<PipelinesRepositoryMetadataUserMember, Integer> {

    Collection<PipelinesRepositoryMetadataUserMember> getMembersWithUser(String userName) throws ServiceException;

    Collection<PipelinesRepositoryMetadataUserMember> getMembersOfRepository(int repositoryId) throws ServiceException;

}
