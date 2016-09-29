package pl.natekrank.model.dto;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
public class TaskDto {
    @JsonManagedReference
    private List<QuestionDto> questions;
}
