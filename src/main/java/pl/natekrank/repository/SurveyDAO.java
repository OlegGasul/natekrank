package pl.natekrank.repository;

import pl.natekrank.model.Survey;
import pl.natekrank.model.Survey;

import java.util.List;

public interface SurveyDAO {
    List<Survey> getAllSurveys();
    Survey save(Survey task);
    Survey getSurvey(Long id);
    Survey getSurvey(String testKey);
}
