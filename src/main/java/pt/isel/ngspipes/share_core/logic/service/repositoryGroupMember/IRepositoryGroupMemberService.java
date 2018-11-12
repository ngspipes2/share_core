package pt.isel.ngspipes.share_core.logic.service.repositoryGroupMember;

import pt.isel.ngspipes.share_core.logic.domain.RepositoryGroupMember;
import pt.isel.ngspipes.share_core.logic.service.IService;
import pt.isel.ngspipes.share_core.logic.service.exceptions.ServiceException;

import java.util.Collection;

public interface IRepositoryGroupMemberService extends IService<RepositoryGroupMember, Integer> {

    Collection<RepositoryGroupMember> getMembersWithGroup(String groupName) throws ServiceException;

    Collection<RepositoryGroupMember> getMembersOfRepository(String repositoryName) throws ServiceException;

    void deleteMembersWithGroup(String groupName) throws ServiceException;

    void deleteMembersOfRepository(String repositoryName) throws ServiceException;

}
