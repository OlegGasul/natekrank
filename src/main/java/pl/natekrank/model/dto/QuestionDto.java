package pl.natekrank.model.dto;

import lombok.Getter;
import lombok.Setter;
import pl.natekrank.model.Answer;

import java.util.List;

@Getter
@Setter
public class QuestionDto {
    private Long id;
    private List<AnswerDto> answers;
    private boolean multiSelect;
    private String text;
}
