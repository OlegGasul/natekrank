package pl.natekrank.web.helper;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.stereotype.Component;
import pl.natekrank.model.Survey;
import pl.natekrank.model.dto.SurveyDto;

@Component
public class DtoFactory {

    public SurveyDto createDto(Survey survey) {
        Mapper mapper = new DozerBeanMapper();
        SurveyDto surveyDto = mapper.map(survey, SurveyDto.class);
        return surveyDto;
    }
}
