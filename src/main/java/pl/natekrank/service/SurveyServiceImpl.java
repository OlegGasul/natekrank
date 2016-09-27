package pl.natekrank.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.natekrank.helpers.SurveyHelper;
import pl.natekrank.model.Survey;
import pl.natekrank.repository.SurveyDAO;
import java.util.List;

@Service
public class SurveyServiceImpl implements SurveyService {
    @Autowired
    private SurveyDAO surveyDAO;

    @Override
    public Survey save(Survey survey) {
        if (survey.getSurveyKey() == null) {
            survey.setSurveyKey(SurveyHelper.generateKey());
        }
        return surveyDAO.save(survey);
    }

    @Override
    public List<Survey> getAllSurveys() {
        return surveyDAO.getAllSurveys();
    }

    @Override
    public Survey getSurveyById(Long id) {
        return surveyDAO.getSurvey(id);
    }

    @Override
    public Survey getSurveyByKey(String surveyKey) {
        return surveyDAO.getSurvey(surveyKey);
    }
}
