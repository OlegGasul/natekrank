package pl.natekrank.model.dto;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;
import pl.natekrank.model.Answer;

import java.util.List;

@Getter
@Setter
public class QuestionDto {
    private Long id;

    @JsonManagedReference
    private List<AnswerDto> answers;

    private boolean multiSelect;
    private String text;
}
