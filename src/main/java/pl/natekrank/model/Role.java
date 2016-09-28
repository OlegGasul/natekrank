package pl.natekrank.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "user_roles")
@Getter
@Setter
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "role_id")
    private Long id;
    private String role;
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private Long user_id;

}
