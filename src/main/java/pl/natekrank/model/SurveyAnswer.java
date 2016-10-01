package pl.natekrank.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;

@Entity
@Table(name = "survey_answers")
@Getter
@Setter
@EqualsAndHashCode(exclude = "id")
public class SurveyAnswer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name="survey_id", insertable = false, updatable = false)
    @JsonBackReference
    private Survey survey;

    @ManyToOne
    @JoinColumn(name="question_id")
    private Question question;

    @ManyToOne
    @JoinColumn(name="selected_answer_id")
    private Answer selectedAnswer;

    @Column(name = "survey_id", insertable = false, updatable = false)
    private Long survey_id;
    @Column(name = "question_id", insertable = false, updatable = false)
    private Long question_id;
    @Column(name = "selected_answer_id", insertable = false, updatable = false)
    private Long selected_answer_id;

    public SurveyAnswer() {
    }

    public SurveyAnswer(Survey survey, Question question, Answer answer) {
        this.survey = survey;
        this.question = question;
        this.selectedAnswer = answer;
    }
}
