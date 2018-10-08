package pt.isel.ngspipes.share_core.logic.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
public class GroupMember {

    @Id
    @SequenceGenerator(name="group_member_sequence", sequenceName="group_member_id_seq", allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "group_member_sequence")
    private int id;
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    @NotNull
    private Date creationDate;
    public Date getCreationDate() { return creationDate; }
    public void setCreationDate(Date creationDate) { this.creationDate = creationDate; }

    @ManyToOne(targetEntity = User.class, optional = false, fetch = FetchType.EAGER)
    private User user;
    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    @ManyToOne(targetEntity = Group.class, optional = false, fetch = FetchType.EAGER)
    private Group group;
    public Group getGroup() { return group; }
    public void setGroup(Group group) { this.group = group; }

    private boolean writeAccess;
    public boolean getWriteAccess() { return writeAccess; }
    public void setWriteAccess(boolean writeAccess) { this.writeAccess = writeAccess; }



    public GroupMember(int id, Date creationDate, User user, Group group, boolean writeAccess) {
        this.id = id;
        this.creationDate = creationDate;
        this.user = user;
        this.group = group;
        this.writeAccess = writeAccess;
    }

    public GroupMember() { }

}
