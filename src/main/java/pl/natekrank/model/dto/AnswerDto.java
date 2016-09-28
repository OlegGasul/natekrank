package pl.natekrank.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AnswerDto {
    private Long id;
    private String text;
    private int orderNum;
    private boolean checked = false;
}
