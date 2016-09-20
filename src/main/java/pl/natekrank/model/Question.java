package pl.natekrank.model;

import lombok.Getter;
import lombok.Setter;
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
    private Task task;

    private String text;

    @Column(name = "multiselect")
    private boolean multiSelect;

    private int order;
}
