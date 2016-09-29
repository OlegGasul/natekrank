package pl.natekrank.model.dto;

import lombok.Getter;
import lombok.Setter;
import org.codehaus.jackson.annotate.JsonBackReference;

@Getter
@Setter
public class AnswerDto {
    private Long id;

    @JsonBackReference
    private QuestionDto question;

    private String text;
    private int orderNum;
    private boolean checked = false;
}
