package pt.isel.ngspipes.share_core.logic.service.toolsRepositoryMetadataGroupMember;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import pt.isel.ngspipes.share_core.dataAccess.RepositoryException;
import pt.isel.ngspipes.share_core.logic.service.Service;
import pt.isel.ngspipes.share_core.logic.service.ServiceUtils;
import pt.isel.ngspipes.share_core.logic.service.exceptions.ServiceException;
import pt.isel.ngspipes.share_core.dataAccess.toolsRepositoryMetadataGroupMember.IToolsRepositoryMetadataGroupMemberRepository;
import pt.isel.ngspipes.share_core.logic.domain.ToolsRepositoryMetadataGroupMember;

import java.util.Collection;
import java.util.Date;

@org.springframework.stereotype.Service
public class ToolsRepositoryMetadataGroupMemberService extends Service<ToolsRepositoryMetadataGroupMember, Integer> implements IToolsRepositoryMetadataGroupMemberService {

    @Autowired
    private IToolsRepositoryMetadataGroupMemberRepository groupMemberRepository;



    protected ToolsRepositoryMetadataGroupMemberService() {
        super("RepositoryGroupMembers", "ToolsRepositoryMetadataGroupMember");
    }



    @Override
    @Transactional
    public void insert(ToolsRepositoryMetadataGroupMember member) throws ServiceException {
        member.setCreationDate(new Date());
        super.insert(member);
    }

    @Override
    protected void validateInsert(ToolsRepositoryMetadataGroupMember member) throws ServiceException { }

    @Override
    protected void validateDelete(Integer id) throws ServiceException { }

    @Override
    protected void validateUpdate(ToolsRepositoryMetadataGroupMember member) throws ServiceException {
        ToolsRepositoryMetadataGroupMember savedMember = getById(member.getId());

        if(savedMember != null) {
            if(!ServiceUtils.sameDate(savedMember.getCreationDate(), member.getCreationDate()))
                throw new ServiceException("Members's creationDate Cannot be changed!");

            if(!savedMember.getGroup().getGroupName().equals(member.getGroup().getGroupName()))
                throw new ServiceException("Members's group Cannot be changed!");

            if(savedMember.getRepository().getId() != member.getRepository().getId())
                throw new ServiceException("Members's repository Cannot be changed!");
        }
    }

    @Override
    protected Integer getId(ToolsRepositoryMetadataGroupMember member) throws ServiceException {
        return member.getId();
    }

    @Override
    public Collection<ToolsRepositoryMetadataGroupMember> getMembersWithGroup(String groupName) throws ServiceException {
        try {
            return groupMemberRepository.getMembersWithGroup(groupName);
        } catch (RepositoryException e) {
            throw new ServiceException("Error getting Members with Group!");
        }
    }

    @Override
    public Collection<ToolsRepositoryMetadataGroupMember> getMembersOfRepository(int repositoryId) throws ServiceException {
        try {
            return groupMemberRepository.getMembersOfRepository(repositoryId);
        } catch (RepositoryException e) {
            throw new ServiceException("Error getting Members of Repository!");
        }
    }

}
