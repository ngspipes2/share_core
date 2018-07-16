package pt.isel.ngspipes.share_core.logic.domain;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "_User")
public class User {

    public enum Role {
        ADMIN,
        NORMAL
    }



    @Id
    @NotEmpty
    @NotNull
    private String userName;
    public String getUserName() { return userName; }
    public void setUserName(String userName) { this.userName = userName; }

    @NotEmpty
    @NotNull
    private String password;
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    private String email;
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    private String gravatarEmail;
    public String getGravatarEmail() { return gravatarEmail; }
    public void setGravatarEmail(String gravatarEmail) { this.gravatarEmail = gravatarEmail; }

    @NotNull
    private Date creationDate;
    public Date getCreationDate() { return creationDate; }
    public void setCreationDate(Date creationDate) { this.creationDate = creationDate; }

    @NotNull
    @Enumerated(EnumType.STRING)
    private Role role;
    public Role getRole() { return this.role; }
    public void setRole(Role role) { this.role = role; }



    public User(String userName, String password, String email, String gravatarEmail, Date creationDate, Role role) {
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.gravatarEmail = gravatarEmail;
        this.creationDate = creationDate;
        this.role = role;
    }

    public User() { }

}
