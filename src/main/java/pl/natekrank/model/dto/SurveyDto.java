package pl.natekrank.model.dto;

import lombok.Getter;
import lombok.Setter;
import pl.natekrank.model.SurveyAnswer;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class SurveyDto {
    private Long id;
    private TaskDto task;
    private int minutesForSolving;
    private Date dueTo;
    private String surveyKey;
}
