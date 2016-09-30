package pl.natekrank.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;

@Entity
@Table(name = "answers")
@Getter
@Setter
@EqualsAndHashCode(exclude = "id")
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name="question_id", insertable = false, updatable = false)
    @JsonBackReference
    private Question question;

    @Column(name = "question_id", insertable = false, updatable = false)
    private Long question_id;

    private String text;

    @Column(name = "is_right")
    private boolean right;
    private int orderNum;
}
