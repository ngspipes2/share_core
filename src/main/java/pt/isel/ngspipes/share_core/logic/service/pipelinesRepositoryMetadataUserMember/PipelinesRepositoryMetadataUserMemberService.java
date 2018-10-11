package pt.isel.ngspipes.share_core.logic.service.pipelinesRepositoryMetadataUserMember;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import pt.isel.ngspipes.share_core.dataAccess.RepositoryException;
import pt.isel.ngspipes.share_core.dataAccess.pipelinesRepositoryMetadataUserMember.IPipelinesRepositoryMetadataUserMemberRepository;
import pt.isel.ngspipes.share_core.logic.domain.PipelinesRepositoryMetadataUserMember;
import pt.isel.ngspipes.share_core.logic.service.Service;
import pt.isel.ngspipes.share_core.logic.service.ServiceUtils;
import pt.isel.ngspipes.share_core.logic.service.exceptions.ServiceException;

import java.util.Collection;
import java.util.Date;

@org.springframework.stereotype.Service
public class PipelinesRepositoryMetadataUserMemberService extends Service<PipelinesRepositoryMetadataUserMember, Integer> implements IPipelinesRepositoryMetadataUserMemberService {

    @Autowired
    private IPipelinesRepositoryMetadataUserMemberRepository userMemberRepository;



    protected PipelinesRepositoryMetadataUserMemberService() {
        super("RepositoryUserMembers", "PipelinesRepositoryMetadataUserMember");
    }



    @Override
    @Transactional
    public void insert(PipelinesRepositoryMetadataUserMember member) throws ServiceException {
        member.setCreationDate(new Date());
        super.insert(member);
    }

    @Override
    protected void validateInsert(PipelinesRepositoryMetadataUserMember member) throws ServiceException { }

    @Override
    protected void validateDelete(Integer id) throws ServiceException { }

    @Override
    protected void validateUpdate(PipelinesRepositoryMetadataUserMember member) throws ServiceException {
        PipelinesRepositoryMetadataUserMember savedMember = getById(member.getId());

        if(savedMember != null) {
            if(!ServiceUtils.sameDate(savedMember.getCreationDate(), member.getCreationDate()))
                throw new ServiceException("Members's creationDate Cannot be changed!");

            if(!savedMember.getUser().getUserName().equals(member.getUser().getUserName()))
                throw new ServiceException("Members's user Cannot be changed!");

            if(savedMember.getRepository().getId() != member.getRepository().getId())
                throw new ServiceException("Members's repository Cannot be changed!");
        }
    }

    @Override
    protected Integer getId(PipelinesRepositoryMetadataUserMember member) throws ServiceException {
        return member.getId();
    }

    @Override
    public Collection<PipelinesRepositoryMetadataUserMember> getMembersWithUser(String userName) throws ServiceException {
        try {
            return userMemberRepository.getMembersWithUser(userName);
        } catch (RepositoryException e) {
            throw new ServiceException("Error getting Members with User!");
        }
    }

    @Override
    public Collection<PipelinesRepositoryMetadataUserMember> getMembersOfRepository(int repositoryId) throws ServiceException {
        try {
            return userMemberRepository.getMembersOfRepository(repositoryId);
        } catch (RepositoryException e) {
            throw new ServiceException("Error getting Members of Repository!");
        }
    }

}
