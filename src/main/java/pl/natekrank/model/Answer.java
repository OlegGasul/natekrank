package pl.natekrank.model;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;

@Entity
@Table(name = "answers")
@Getter
@Setter
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long question_id;
    private String text;

    @Column(name = "is_right")
    private boolean right;
    private int orderNum;
}
