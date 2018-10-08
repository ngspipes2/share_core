package pt.isel.ngspipes.share_core.logic.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
public class AccessToken {

    @Id
    @SequenceGenerator(name="access_token_sequence", sequenceName="access_token_id_seq", allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "access_token_sequence")
    private int id;
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    @ManyToOne(targetEntity = User.class, optional = false, fetch = FetchType.EAGER)
    private User owner;
    public User getOwner() { return owner; }
    public void setOwner(User owner) { this.owner = owner; }

    private String token;
    public String getToken() { return token; }
    public void setToken(String token) { this.token = token; }

    @NotNull
    private Date creationDate;
    public Date getCreationDate() { return creationDate; }
    public void setCreationDate(Date creationDate) { this.creationDate = creationDate; }

    private String description;
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    private boolean writeAccess;
    public boolean getWriteAccess() { return writeAccess; }
    public void setWriteAccess(boolean writeAccess) { this.writeAccess = writeAccess; }



    public AccessToken(int id, User owner, String token, Date creationDate, String description, boolean writeAccess) {
        this.id = id;
        this.owner = owner;
        this.token = token;
        this.creationDate = creationDate;
        this.description = description;
        this.writeAccess = writeAccess;
    }

    public AccessToken() { }

}
