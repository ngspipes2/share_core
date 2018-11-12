package pt.isel.ngspipes.share_core.logic.service.repositoryGroupMember;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import pt.isel.ngspipes.share_core.dataAccess.RepositoryException;
import pt.isel.ngspipes.share_core.dataAccess.repositoryGroupMember.IRepositoryGroupMemberRepository;
import pt.isel.ngspipes.share_core.logic.domain.RepositoryGroupMember;
import pt.isel.ngspipes.share_core.logic.service.Service;
import pt.isel.ngspipes.share_core.logic.service.ServiceUtils;
import pt.isel.ngspipes.share_core.logic.service.exceptions.ServiceException;

import java.util.Collection;
import java.util.Date;

@org.springframework.stereotype.Service
public class RepositoryGroupMemberService extends Service<RepositoryGroupMember, Integer> implements IRepositoryGroupMemberService {

    @Autowired
    private IRepositoryGroupMemberRepository groupMemberRepository;



    public RepositoryGroupMemberService() {
        super("RepositoryGroupMembers", "RepositoryGroupMember");
    }



    @Override
    @Transactional
    public void insert(RepositoryGroupMember member) throws ServiceException {
        member.setCreationDate(new Date());
        super.insert(member);
    }

    @Override
    protected void validateInsert(RepositoryGroupMember member) throws ServiceException {
        for(RepositoryGroupMember groupMember : getMembersOfRepository(member.getRepository().getRepositoryName()))
            if(groupMember.getGroup().getGroupName().equals(member.getGroup().getGroupName()))
                throw new ServiceException("Group " + member.getGroup().getGroupName() + " is already member of repository " + member.getRepository().getRepositoryName() + "!");
    }

    @Override
    protected void validateDelete(Integer id) throws ServiceException { }

    @Override
    protected void validateUpdate(RepositoryGroupMember member) throws ServiceException {
        RepositoryGroupMember savedMember = getById(member.getId());

        if(savedMember != null) {
            if(!ServiceUtils.sameDate(savedMember.getCreationDate(), member.getCreationDate()))
                throw new ServiceException("Members's creationDate Cannot be changed!");

            if(!savedMember.getGroup().getGroupName().equals(member.getGroup().getGroupName()))
                throw new ServiceException("Members's group Cannot be changed!");

            if(!savedMember.getRepository().getRepositoryName().equals(member.getRepository().getRepositoryName()))
                throw new ServiceException("Members's repository Cannot be changed!");
        }
    }

    @Override
    protected Integer getId(RepositoryGroupMember member) throws ServiceException {
        return member.getId();
    }

    @Override
    public Collection<RepositoryGroupMember> getMembersWithGroup(String groupName) throws ServiceException {
        try {
            return groupMemberRepository.getMembersWithGroup(groupName);
        } catch (RepositoryException e) {
            throw new ServiceException("Error getting Members with Group!");
        }
    }

    @Override
    public Collection<RepositoryGroupMember> getMembersOfRepository(String repositoryName) throws ServiceException {
        try {
            return groupMemberRepository.getMembersOfRepository(repositoryName);
        } catch (RepositoryException e) {
            throw new ServiceException("Error getting Members of repository:" + repositoryName + "!");
        }
    }

    @Override
    @Transactional
    public void deleteMembersWithGroup(String groupName) throws ServiceException {
        try {
            groupMemberRepository.deleteMembersWithGroup(groupName);
        } catch (RepositoryException e) {
            throw new ServiceException("Error deleting members with group:" + groupName + "!", e);
        }
    }

    @Override
    @Transactional
    public void deleteMembersOfRepository(String repositoryName) throws ServiceException {
        try {
            groupMemberRepository.deleteMembersOfRepository(repositoryName);
        } catch (RepositoryException e) {
            throw new ServiceException("Error deleting members of repository:" + repositoryName + "!", e);
        }
    }

}
