package pt.isel.ngspipes.share_core.logic.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "ToolsRepositoryMetadataUserMember")
public class ToolsRepositoryMetadataUserMember {

    @Id
    @SequenceGenerator(name="tools_repository_user_member_sequence", sequenceName="tools_repository_user_member_id_seq", allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tools_repository_user_member_sequence")
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

    @ManyToOne(targetEntity = ToolsRepositoryMetadata.class, optional = false, fetch = FetchType.EAGER)
    private ToolsRepositoryMetadata repository;
    public ToolsRepositoryMetadata getRepository() { return repository; }
    public void setRepository(ToolsRepositoryMetadata repository) { this.repository = repository; }

    private boolean writeAccess;
    public boolean getWriteAccess() { return writeAccess; }
    public void setWriteAccess(boolean writeAccess) { this.writeAccess = writeAccess; }



    public ToolsRepositoryMetadataUserMember(int id, Date creationDate, User user, ToolsRepositoryMetadata repository, boolean writeAccess) {
        this.id = id;
        this.creationDate = creationDate;
        this.user = user;
        this.repository = repository;
        this.writeAccess = writeAccess;
    }

    public ToolsRepositoryMetadataUserMember() { }

}
