package pl.natekrank.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.natekrank.model.Survey;
import pl.natekrank.repository.SurveyDAO;
import java.util.List;

@Service
public class SurveyServiceImpl implements SurveyService {
    @Autowired
    private SurveyDAO surveyDAO;

    @Override
    public Survey save(Survey survey) {
        return surveyDAO.save(survey);
    }

    @Override
    public List<Survey> getAllSurveys() {
        return surveyDAO.getAllSurveys();
    }
}
