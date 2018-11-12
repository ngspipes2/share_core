package pt.isel.ngspipes.share_core.logic.service.repositoryUserMember;

import pt.isel.ngspipes.share_core.logic.domain.RepositoryUserMember;
import pt.isel.ngspipes.share_core.logic.service.IService;
import pt.isel.ngspipes.share_core.logic.service.exceptions.ServiceException;

import java.util.Collection;

public interface IRepositoryUserMemberService extends IService<RepositoryUserMember, Integer> {

    Collection<RepositoryUserMember> getMembersWithUser(String userName) throws ServiceException;

    Collection<RepositoryUserMember> getMembersOfRepository(String repositoryName) throws ServiceException;

    void deleteMembersWithUser(String userName) throws ServiceException;

    void deleteMembersOfRepository(String repositoryName) throws ServiceException;

}
