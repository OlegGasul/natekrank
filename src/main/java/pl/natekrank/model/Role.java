package pl.natekrank.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "user_roles")
@Getter
@Setter
@EqualsAndHashCode(exclude = "id")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    private String role;
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private Long user_id;

}
