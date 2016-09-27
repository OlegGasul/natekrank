package pl.natekrank.service;

import pl.natekrank.model.Task;
import pl.natekrank.model.Survey;
import java.util.List;

public interface SurveyService {
    Survey save(Survey survey);
    List<Survey> getAllSurveys();
}
