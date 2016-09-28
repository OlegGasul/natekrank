package pl.natekrank.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.natekrank.helpers.SurveyHelper;
import pl.natekrank.model.Question;
import pl.natekrank.model.Survey;
import pl.natekrank.model.Task;
import pl.natekrank.model.dto.QuestionDto;
import pl.natekrank.model.dto.SurveyDto;
import pl.natekrank.model.dto.TaskDto;
import pl.natekrank.repository.SurveyDAO;
import pl.natekrank.repository.TaskDAO;

import java.util.Date;
import java.util.LinkedList;
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

    @Override
    public Survey startSurvey(Survey survey) {
        if (survey.getStarted() == null) {
            survey.setStarted(new Date());
            survey = surveyDAO.save(survey);
        }

        return survey;
    }

    @Override
    public Survey submitSurvey(SurveyDto surveyDto) {
        Survey survey = surveyDAO.getSurvey(surveyDto.getSurveyKey());
        Task task = survey.getTask();
        survey.setSurveyAnswers(new LinkedList<>());

        TaskDto surveyTask = surveyDto.getTask();

        List<Question> questions = task.getQuestions();
        for (Question question : questions) {
            QuestionDto questionDto = surveyTask.getQuestions()
                .stream()
                .filter(q -> q.getId() == question.getId())
                .findFirst().get();
        }

        return survey;
    }
}
