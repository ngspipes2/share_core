package pt.isel.ngspipes.share_core.dataAccess.group;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.springframework.stereotype.Repository;
import pt.isel.ngspipes.share_core.dataAccess.PostgresRepository;
import pt.isel.ngspipes.share_core.dataAccess.RepositoryException;
import pt.isel.ngspipes.share_core.logic.domain.Group;

import java.util.Collection;
import java.util.stream.Collectors;

@Repository
public class GroupRepository extends PostgresRepository<Group, String> implements IGroupRepository {

    public GroupRepository() {
        super(Group.class);
    }



    @Override
    protected void setId(Group group, String groupName) {
        group.setGroupName(groupName);
    }

    @Override
    protected String getId(Group group) {
        return group.getGroupName();
    }

    @Override
    protected void merge(Group group, Group groupToUpdate) {
        groupToUpdate.setGroupName(group.getGroupName());
        groupToUpdate.setDescription(group.getDescription());
        groupToUpdate.setOwner(group.getOwner());
        groupToUpdate.setCreationDate(group.getCreationDate());
    }

    @Override
    public Collection<Group> getGroupsOfUser(String userName) throws RepositoryException {
        return getAll().stream().filter((group) -> group.getOwner().getUserName().equals(userName)).collect(Collectors.toList());
    }

    @Override
    public Collection<String> getGroupsNames() throws RepositoryException {
        Session session = this.hibernateTemplate.getSessionFactory().getCurrentSession();
        Criteria criteria = session.createCriteria(Group.class);
        criteria.setProjection(Projections.projectionList().add(Projections.property("groupName")));

        return criteria.list();
    }

    @Override
    public void deleteGroupsOfUser(String userName) throws RepositoryException {
        super.hibernateTemplate.deleteAll(getGroupsOfUser(userName));
    }

}
