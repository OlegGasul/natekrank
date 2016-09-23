package pl.natekrank.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;

@Entity
@Table(name = "tests_taken_answers")
@Getter
@Setter
public class TestTakenAnswer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name="test_taken_id", insertable = false, updatable = false)
    @JsonBackReference
    private TestTaken ticket;

    @ManyToOne
    @JoinColumn(name="question_id", insertable = false, updatable = false)
    @JsonBackReference
    private Question question;

    @ManyToOne
    @JoinColumn(name="selected_answer_id", insertable = false, updatable = false)
    @JsonBackReference
    private Answer selectedAnswer;

    @Column(name = "question_id", insertable = false, updatable = false)
    private Long question_id;
    @Column(name = "ticket_id", insertable = false, updatable = false)
    private Long test_taken_id;
    @Column(name = "selected_answer_id", insertable = false, updatable = false)
    private Long selected_answer_id;
}
