package pt.isel.ngspipes.share_core.logic.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "PipelinesRepositoryMetadataGroupMember")
public class PipelinesRepositoryMetadataGroupMember {

    @Id
    @SequenceGenerator(name="pipelines_repository_group_member_sequence", sequenceName="pipelines_repository_group_member_id_seq", allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pipelines_repository_group_member_sequence")
    private int id;
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    @NotNull
    private Date creationDate;
    public Date getCreationDate() { return creationDate; }
    public void setCreationDate(Date creationDate) { this.creationDate = creationDate; }

    @ManyToOne(targetEntity = Group.class, optional = false, fetch = FetchType.EAGER)
    private Group group;
    public Group getGroup() { return group; }
    public void setGroup(Group group) { this.group = group; }

    @ManyToOne(targetEntity = PipelinesRepositoryMetadata.class, optional = false, fetch = FetchType.EAGER)
    private PipelinesRepositoryMetadata repository;
    public PipelinesRepositoryMetadata getRepository() { return repository; }
    public void setRepository(PipelinesRepositoryMetadata repository) { this.repository = repository; }

    private boolean writeAccess;
    public boolean getWriteAccess() { return writeAccess; }
    public void setWriteAccess(boolean writeAccess) { this.writeAccess = writeAccess; }



    public PipelinesRepositoryMetadataGroupMember(int id, Date creationDate, Group group, PipelinesRepositoryMetadata repository, boolean writeAccess) {
        this.id = id;
        this.creationDate = creationDate;
        this.group = group;
        this.repository = repository;
        this.writeAccess = writeAccess;
    }

    public PipelinesRepositoryMetadataGroupMember() { }

}
