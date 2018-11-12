package pt.isel.ngspipes.share_core.logic.domain;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
public class RepositoryInfo {

    public enum EntityType {
        TOOLS,
        PIPELINES
    }

    public enum LocationType {
        INTERNAL,
        EXTERNAL
    }



    @Id
    @NotNull
    @NotEmpty
    private String repositoryName;
    public String getRepositoryName() { return this.repositoryName; }
    public void setRepositoryName(String repositoryName) { this.repositoryName = repositoryName; }

    @NotNull
    private EntityType entityType;
    public EntityType getEntityType() { return this.entityType; }
    public void setEntityType(EntityType entityType) { this.entityType = entityType; }

    @NotNull
    private LocationType locationType;
    public LocationType getLocationType() { return this.locationType; }
    public void setLocationType(LocationType locationType) { this.locationType = locationType; }

    private String description;
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    @NotNull
    private Date creationDate;
    public Date getCreationDate() { return creationDate; }
    public void setCreationDate(Date creationDate) { this.creationDate = creationDate; }

    private boolean isPublic;
    public boolean getIsPublic() { return isPublic; }
    public void setPublic(boolean aPublic) { isPublic = aPublic; }

    @ManyToOne(targetEntity = User.class, optional = false, fetch = FetchType.EAGER)
    private User owner;
    public User getOwner() { return owner; }
    public void setOwner(User owner) { this.owner = owner; }

    @NotNull
    @NotEmpty
    private String location;
    public String getLocation() { return this.location; }
    public void setLocation(String location) { this.location = location; }



    public RepositoryInfo(String repositoryName, EntityType entityType, LocationType locationType, String description, Date creationDate, boolean isPublic, User owner, String location) {
        this.repositoryName = repositoryName;
        this.entityType = entityType;
        this.locationType = locationType;
        this.description = description;
        this.creationDate = creationDate;
        this.isPublic = isPublic;
        this.owner = owner;
        this.location = location;
    }

    public RepositoryInfo() { }

}
