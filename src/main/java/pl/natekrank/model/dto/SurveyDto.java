package pl.natekrank.model.dto;

import lombok.Getter;
import lombok.Setter;
import java.util.Date;

@Getter
@Setter
public class SurveyDto {
    private Long id;
    private TaskDto task;
    private Date started;
    private Date serverRequest;
    private int minutesForSolving;
    private Date dueTo;
    private String token;
    private int score;
}
