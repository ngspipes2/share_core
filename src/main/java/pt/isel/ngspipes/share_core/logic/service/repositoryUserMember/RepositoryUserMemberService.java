package pt.isel.ngspipes.share_core.logic.service.repositoryUserMember;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import pt.isel.ngspipes.share_core.dataAccess.RepositoryException;
import pt.isel.ngspipes.share_core.dataAccess.repositoryUserMember.IRepositoryUserMemberRepository;
import pt.isel.ngspipes.share_core.logic.domain.RepositoryUserMember;
import pt.isel.ngspipes.share_core.logic.service.Service;
import pt.isel.ngspipes.share_core.logic.service.ServiceUtils;
import pt.isel.ngspipes.share_core.logic.service.exceptions.ServiceException;

import java.util.Collection;
import java.util.Date;

@org.springframework.stereotype.Service
public class RepositoryUserMemberService extends Service<RepositoryUserMember, Integer> implements IRepositoryUserMemberService {

    @Autowired
    private IRepositoryUserMemberRepository userMemberRepository;



    public RepositoryUserMemberService() {
        super("RepositoryUserMembers", "RepositoryUserMember");
    }



    @Override
    @Transactional
    public void insert(RepositoryUserMember member) throws ServiceException {
        member.setCreationDate(new Date());
        super.insert(member);
    }

    @Override
    protected void validateInsert(RepositoryUserMember member) throws ServiceException {
        for(RepositoryUserMember userMember : getMembersOfRepository(member.getRepository().getRepositoryName()))
            if(userMember.getUser().getUserName().equals(member.getUser().getUserName()))
                throw new ServiceException("User " + member.getUser().getUserName() + " is already member of repository " + member.getRepository().getRepositoryName() + "!");
    }

    @Override
    protected void validateDelete(Integer id) throws ServiceException { }

    @Override
    protected void validateUpdate(RepositoryUserMember member) throws ServiceException {
        RepositoryUserMember savedMember = getById(member.getId());

        if(savedMember != null) {
            if(!ServiceUtils.sameDate(savedMember.getCreationDate(), member.getCreationDate()))
                throw new ServiceException("Members's creationDate Cannot be changed!");

            if(!savedMember.getUser().getUserName().equals(member.getUser().getUserName()))
                throw new ServiceException("Members's user Cannot be changed!");

            if(!savedMember.getRepository().getRepositoryName().equals(member.getRepository().getRepositoryName()))
                throw new ServiceException("Members's repository Cannot be changed!");
        }
    }

    @Override
    protected Integer getId(RepositoryUserMember member) throws ServiceException {
        return member.getId();
    }

    @Override
    public Collection<RepositoryUserMember> getMembersWithUser(String userName) throws ServiceException {
        try {
            return userMemberRepository.getMembersWithUser(userName);
        } catch (RepositoryException e) {
            throw new ServiceException("Error getting Members with User!");
        }
    }

    @Override
    public Collection<RepositoryUserMember> getMembersOfRepository(String repositoryName) throws ServiceException {
        try {
            return userMemberRepository.getMembersOfRepository(repositoryName);
        } catch (RepositoryException e) {
            throw new ServiceException("Error getting Members of repository:" + repositoryName + "!");
        }
    }

    @Override
    @Transactional
    public void deleteMembersWithUser(String userName) throws ServiceException {
        try {
            userMemberRepository.deleteMembersWithUser(userName);
        } catch (RepositoryException e) {
            throw new ServiceException("Error deleting members with user:" + userName + "!", e);
        }
    }

    @Override
    @Transactional
    public void deleteMembersOfRepository(String repositoryName) throws ServiceException {
        try {
            userMemberRepository.deleteMembersOfRepository(repositoryName);
        } catch (RepositoryException e) {
            throw new ServiceException("Error deleting members of repository:" + repositoryName + "!", e);
        }
    }

}
