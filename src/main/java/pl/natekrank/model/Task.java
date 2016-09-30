package pl.natekrank.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "tasks")
@Getter
@Setter
@EqualsAndHashCode(exclude = "id")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;

    @Column(name = "owner_id", insertable = false, updatable = false)
    private Long owner_id;

    @Column(name = "days_expired")
    private int daysExpired;

    @Column(name = "minutes_for_solving")
    private int minutesForSolving;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    private Date created;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    private Date modified;

    @OneToMany(fetch = FetchType.LAZY)
    @Cascade(value = {org.hibernate.annotations.CascadeType.ALL})
    @JoinColumn(name = "task_id", nullable = false, insertable = false, updatable = false)
    @JsonManagedReference
    private List<Question> questions;

    private void clearQuestions() {
        this.questions.clear();
    }

    private void addQuestions(Collection<Question> questions) {
        this.questions.addAll(questions);
    }

    public void setQuestions(List<Question> questions) {
        this.clearQuestions();
        if (questions != null) {
            this.addQuestions(questions);
        }
    }
}
