package pl.natekrank.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.natekrank.repository.SurveyDAO;

@Service
public class InterviewServiceImpl implements InterviewService {

    @Autowired
    private SurveyDAO surveyDAO;

    @Override
    public boolean checkKey(String testKey) {
        surveyDAO.getSurvey(testKey);
        return false;
    }
}
