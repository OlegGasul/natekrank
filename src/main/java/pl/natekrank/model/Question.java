package pl.natekrank.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "questions")
@Getter
@Setter
@EqualsAndHashCode(exclude = "id")
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="task_id", insertable = false, updatable = false)
    @JsonBackReference
    private Task task;

    @OneToMany(fetch = FetchType.LAZY)
    @Cascade(value = {org.hibernate.annotations.CascadeType.ALL})
    @JoinColumn(name = "question_id", nullable = false, insertable = false, updatable = false)
    @JsonManagedReference
    private List<Answer> answers;

    @Column(name = "task_id", insertable = false, updatable = false)
    private Long task_id;

    private String text;

    @Column(name = "multiselect")
    private boolean multiSelect;

    private int orderNum;
}
