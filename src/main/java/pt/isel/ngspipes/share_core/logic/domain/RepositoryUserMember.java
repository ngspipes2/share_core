package pt.isel.ngspipes.share_core.logic.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
public class RepositoryUserMember {

    @Id
    @SequenceGenerator(name="repository_user_member_sequence", sequenceName="repository_user_member_id_seq", allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "repository_user_member_sequence")
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

    @ManyToOne(targetEntity = RepositoryInfo.class, optional = false, fetch = FetchType.EAGER)
    private RepositoryInfo repository;
    public RepositoryInfo getRepository() { return repository; }
    public void setRepository(RepositoryInfo repository) { this.repository = repository; }

    private boolean writeAccess;
    public boolean getWriteAccess() { return writeAccess; }
    public void setWriteAccess(boolean writeAccess) { this.writeAccess = writeAccess; }



    public RepositoryUserMember(int id, Date creationDate, User user, RepositoryInfo repository, boolean writeAccess) {
        this.id = id;
        this.creationDate = creationDate;
        this.user = user;
        this.repository = repository;
        this.writeAccess = writeAccess;
    }

    public RepositoryUserMember() { }

}
