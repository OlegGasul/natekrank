package pl.natekrank.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Cascade;

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

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    private Date created;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    private Date modified;

    @OneToMany(fetch = FetchType.LAZY)
    @Cascade(value = {org.hibernate.annotations.CascadeType.ALL})
    @JoinColumn(name = "task_id", nullable = false, insertable = false, updatable = false)
    @JsonManagedReference
    private List<Question> questions;
}
