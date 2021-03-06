package pt.isel.ngspipes.share_core.logic.domain;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "_Group")
public class Group {

    @Id
    @NotNull
    @NotEmpty
    private String groupName;
    public String getGroupName() { return groupName; }
    public void setGroupName(String groupName) { this.groupName = groupName; }

    private String description;
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    @NotNull
    private Date creationDate;
    public Date getCreationDate() { return creationDate; }
    public void setCreationDate(Date creationDate) { this.creationDate = creationDate; }

    @ManyToOne(targetEntity = User.class, optional = false, fetch = FetchType.EAGER)
    private User owner;
    public User getOwner() { return owner; }
    public void setOwner(User owner) { this.owner = owner; }



    public Group(String groupName, String description, Date creationDate, User owner) {
        this.groupName = groupName;
        this.description = description;
        this.creationDate = creationDate;
        this.owner = owner;
    }

    public Group() { }

}
