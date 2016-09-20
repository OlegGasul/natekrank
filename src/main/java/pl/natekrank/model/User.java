package pl.natekrank.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
public class User {
    private Long id;
    private String email;
    private String password;
    private String passwordConfirm;
    private List<Role> roles;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    @Transient
    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    @OneToMany(fetch=FetchType.EAGER, cascade = {CascadeType.ALL}, mappedBy="user_id")
    public List<Role> getRoles() {
        return roles;
    }
}
