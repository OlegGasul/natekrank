package pl.natekrank.service;

import pl.natekrank.model.Task;
import pl.natekrank.model.Survey;
import pl.natekrank.model.dto.SurveyDto;

import java.util.List;

public interface SurveyService {
    Survey save(Survey survey);
    List<Survey> getAllSurveys();
    Survey getSurveyById(Long id);
    Survey getSurveyByKey(String surveyKey);
    Survey startSurvey(Survey survey);
    Survey submitSurvey(SurveyDto surveyDto);
}
