package pl.natekrank.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "tasks")
@Getter
@Setter
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String description;
    @Column(name = "owner_id", insertable = false, updatable = false)
    private Long owner_id;

    private Date created;
    private Date modified;

    @OneToMany(mappedBy = "task", fetch = FetchType.LAZY)
    private List<Question> questions;
}
