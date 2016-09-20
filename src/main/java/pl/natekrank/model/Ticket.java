package pl.natekrank.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "tickets")
@Getter
@Setter
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String email;

    @Column(name = "ticket_key")
    private Date ticketKey;

    @Column(name = "due_to")
    private Date dueTo;

    private Date started;
    private Date finished;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "minutes_for_solving")
    private int minutesForSolving;

    @OneToMany(mappedBy = "ticket")
    private List<TicketAnswer> ticketAnswers;
}
