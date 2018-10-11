package pt.isel.ngspipes.share_core.logic.service.toolsRepositoryMetadataUserMember;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import pt.isel.ngspipes.share_core.dataAccess.RepositoryException;
import pt.isel.ngspipes.share_core.logic.service.Service;
import pt.isel.ngspipes.share_core.logic.service.ServiceUtils;
import pt.isel.ngspipes.share_core.logic.service.exceptions.ServiceException;
import pt.isel.ngspipes.share_core.dataAccess.toolsRepositoryMetadataUserMember.IToolsRepositoryMetadataUserMemberRepository;
import pt.isel.ngspipes.share_core.logic.domain.ToolsRepositoryMetadataUserMember;

import java.util.Collection;
import java.util.Date;

@org.springframework.stereotype.Service
public class ToolsRepositoryMetadataUserMemberService extends Service<ToolsRepositoryMetadataUserMember, Integer> implements IToolsRepositoryMetadataUserMemberService {

    @Autowired
    private IToolsRepositoryMetadataUserMemberRepository userMemberRepository;



    protected ToolsRepositoryMetadataUserMemberService() {
        super("RepositoryUserMembers", "ToolsRepositoryMetadataUserMember");
    }



    @Override
    @Transactional
    public void insert(ToolsRepositoryMetadataUserMember member) throws ServiceException {
        member.setCreationDate(new Date());
        super.insert(member);
    }

    @Override
    protected void validateInsert(ToolsRepositoryMetadataUserMember member) throws ServiceException { }

    @Override
    protected void validateDelete(Integer id) throws ServiceException { }

    @Override
    protected void validateUpdate(ToolsRepositoryMetadataUserMember member) throws ServiceException {
        ToolsRepositoryMetadataUserMember savedMember = getById(member.getId());

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
    protected Integer getId(ToolsRepositoryMetadataUserMember member) throws ServiceException {
        return member.getId();
    }

    @Override
    public Collection<ToolsRepositoryMetadataUserMember> getMembersWithUser(String userName) throws ServiceException {
        try {
            return userMemberRepository.getMembersWithUser(userName);
        } catch (RepositoryException e) {
            throw new ServiceException("Error getting Members with User!");
        }
    }

    @Override
    public Collection<ToolsRepositoryMetadataUserMember> getMembersOfRepository(int repositoryId) throws ServiceException {
        try {
            return userMemberRepository.getMembersOfRepository(repositoryId);
        } catch (RepositoryException e) {
            throw new ServiceException("Error getting Members of Repository!");
        }
    }

}
