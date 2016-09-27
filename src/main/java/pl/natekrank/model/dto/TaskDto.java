package pl.natekrank.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
public class TaskDto {
    private List<QuestionDto> questions;
}
