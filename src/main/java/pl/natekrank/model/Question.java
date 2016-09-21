package pl.natekrank.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;

@Entity
@Table(name = "questions")
@Getter
@Setter
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name="task_id", insertable = false, updatable = false)
    @JsonBackReference
    private Task task;

    @Column(name = "task_id", insertable = false, updatable = false)
    private Long task_id;

    private String text;

    @Column(name = "multiselect")
    private boolean multiSelect;

    private int orderNum;
}
