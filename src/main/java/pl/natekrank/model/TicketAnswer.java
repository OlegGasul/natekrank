package pl.natekrank.model;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;

@Entity
@Table(name = "ticket_answers")
@Getter
@Setter
public class TicketAnswer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "question_id", insertable = false, updatable = false)
    private Long question_id;
    @Column(name = "ticket_id", insertable = false, updatable = false)
    private Long ticket_id;
    @Column(name = "selected_answer_id", insertable = false, updatable = false)
    private Long selected_answer_id;

    @ManyToOne
    private Ticket ticket;
    @ManyToOne
    private Question question;
    @ManyToOne
    private Answer selectedAnswer;
}
