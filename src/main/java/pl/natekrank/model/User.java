package pl.natekrank.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
@EqualsAndHashCode(exclude = "id")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    private String email;
    private String password;
    @Transient
    private String passwordConfirm;
    @OneToMany(cascade = {CascadeType.ALL}, mappedBy="user_id")
    private List<Role> roles = new ArrayList<>();

}
