package pt.isel.ngspipes.share_core.logic.service.pipelinesRepositoryMetadataGroupMember;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import pt.isel.ngspipes.share_core.dataAccess.RepositoryException;
import pt.isel.ngspipes.share_core.dataAccess.pipelinesRepositoryMetadataGroupMember.IPipelinesRepositoryMetadataGroupMemberRepository;
import pt.isel.ngspipes.share_core.logic.domain.PipelinesRepositoryMetadataGroupMember;
import pt.isel.ngspipes.share_core.logic.service.Service;
import pt.isel.ngspipes.share_core.logic.service.ServiceUtils;
import pt.isel.ngspipes.share_core.logic.service.exceptions.ServiceException;

import java.util.Collection;
import java.util.Date;

@org.springframework.stereotype.Service
public class PipelinesRepositoryMetadataGroupMemberService extends Service<PipelinesRepositoryMetadataGroupMember, Integer> implements IPipelinesRepositoryMetadataGroupMemberService {

    @Autowired
    private IPipelinesRepositoryMetadataGroupMemberRepository groupMemberRepository;



    protected PipelinesRepositoryMetadataGroupMemberService() {
        super("RepositoryGroupMembers", "PipelinesRepositoryMetadataGroupMember");
    }



    @Override
    @Transactional
    public void insert(PipelinesRepositoryMetadataGroupMember member) throws ServiceException {
        member.setCreationDate(new Date());
        super.insert(member);
    }

    @Override
    protected void validateInsert(PipelinesRepositoryMetadataGroupMember member) throws ServiceException { }

    @Override
    protected void validateDelete(Integer id) throws ServiceException { }

    @Override
    protected void validateUpdate(PipelinesRepositoryMetadataGroupMember member) throws ServiceException {
        PipelinesRepositoryMetadataGroupMember savedMember = getById(member.getId());

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
    protected Integer getId(PipelinesRepositoryMetadataGroupMember member) throws ServiceException {
        return member.getId();
    }

    @Override
    public Collection<PipelinesRepositoryMetadataGroupMember> getMembersWithGroup(String groupName) throws ServiceException {
        try {
            return groupMemberRepository.getMembersWithGroup(groupName);
        } catch (RepositoryException e) {
            throw new ServiceException("Error getting Members with Group!");
        }
    }

    @Override
    public Collection<PipelinesRepositoryMetadataGroupMember> getMembersOfRepository(int repositoryId) throws ServiceException {
        try {
            return groupMemberRepository.getMembersOfRepository(repositoryId);
        } catch (RepositoryException e) {
            throw new ServiceException("Error getting Members of Repository!");
        }
    }

}
