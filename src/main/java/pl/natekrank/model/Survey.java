package pl.natekrank.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "surveys")
@Getter
@Setter
@EqualsAndHashCode(exclude = "id")
public class Survey {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name="task_id")
    @JsonBackReference
    private Task task;

    @Column(name = "task_id", insertable = false, updatable = false)
    private Long taskId;

    private String sender;
    private String email;
    private String message;
    private boolean sent;

    @Column(name = "token")
    private String token;

    @Column(name = "due_to")
    private Date dueTo;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    private Date started;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    private Date finished;

    @Column(name = "sender_notified")
    private boolean senderNotified;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "minutes_for_solving")
    private int minutesForSolving;

    @OneToMany(fetch = FetchType.LAZY, orphanRemoval = true)
    @Cascade(value = {org.hibernate.annotations.CascadeType.ALL})
    @JoinColumn(name = "survey_id", nullable = false, insertable = false, updatable = false)
    @JsonManagedReference
    private List<SurveyAnswer> surveyAnswers = new LinkedList<>();

    private void clearSurveyAnswers() {
        this.surveyAnswers.clear();
    }

    private void addSurveyAnswers(Collection<SurveyAnswer> surveyAnswers) {
        this.surveyAnswers.addAll(surveyAnswers);
    }

    public void setSurveyAnswers(List<SurveyAnswer> surveyAnswers) {
        this.clearSurveyAnswers();
        if (surveyAnswers != null) {
            this.addSurveyAnswers(surveyAnswers);
        }
    }

    private Integer score;
}
