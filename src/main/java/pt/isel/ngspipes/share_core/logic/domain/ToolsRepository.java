package pt.isel.ngspipes.share_core.logic.domain;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.Date;

@Entity
public class ToolsRepository {

    @Id
    @SequenceGenerator(name="tools_repository_sequence", sequenceName="tools_repository_id_seq", allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tools_repository_sequence")
    private int id;
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    @NotNull
    @NotEmpty
    private String name;
    public String getName() { return this.name; }
    public void setName(String name) { this.name = name; }

    private String description;
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    @NotNull
    private Date creationDate;
    public Date getCreationDate() { return creationDate; }
    public void setCreationDate(Date creationDate) { this.creationDate = creationDate; }

    private boolean isPublic;
    public boolean isPublic() { return isPublic; }
    public void setPublic(boolean aPublic) { isPublic = aPublic; }

    @ManyToOne(targetEntity = User.class, optional = false, fetch = FetchType.EAGER)
    private User owner;
    public User getOwner() { return owner; }
    public void setOwner(User owner) { this.owner = owner; }

    @LazyCollection(LazyCollectionOption.FALSE)
    @ManyToMany(targetEntity = Group.class)
    private Collection<Group> groupsAccess;
    public Collection<Group> getGroupsAccess() { return groupsAccess; }
    public void setGroupsAccess(Collection<Group> groupsAccess) { this.groupsAccess = groupsAccess; }

    @LazyCollection(LazyCollectionOption.FALSE)
    @ManyToMany(targetEntity = User.class)
    private Collection<User> usersAccess;
    public Collection<User> getUsersAccess() { return usersAccess; }
    public void setUsersAccess(Collection<User> usersAccess) { this.usersAccess = usersAccess; }



    public ToolsRepository(int id, String name, String description, Date creationDate, boolean isPublic, User owner, Collection<Group> groupAccess, Collection<User> usersAccess) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.creationDate = creationDate;
        this.isPublic = isPublic;
        this.owner = owner;
        this.groupsAccess = groupAccess;
        this.usersAccess = usersAccess;
    }

    public ToolsRepository() { }

}
