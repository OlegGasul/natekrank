package pl.natekrank.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.natekrank.helpers.SurveyHelper;
import pl.natekrank.model.*;
import pl.natekrank.model.dto.QuestionDto;
import pl.natekrank.model.dto.SurveyDto;
import pl.natekrank.model.dto.TaskDto;
import pl.natekrank.repository.SurveyDAO;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

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

        survey.getSurveyAnswers().clear();
        Survey surveyEx = surveyDAO.save(survey);

        TaskDto surveyTask = surveyDto.getTask();
        List<SurveyAnswer> surveyAnswers = new LinkedList<>();

        List<Question> questions = task.getQuestions();
        for (Question question : questions) {
            surveyTask.getQuestions()
                    .stream()
                    .filter(q -> q.getId().equals(question.getId()))
                    .findFirst().get().getAnswers().stream()
                    .filter(answerDto -> answerDto.isChecked())
                    .forEach(answerDto -> {
                        Answer answer = question.getAnswers().stream()
                                .filter(a -> answerDto.getId().equals(a.getId()))
                                .findFirst().get();

                        SurveyAnswer surveyAnswer = new SurveyAnswer();
                        surveyAnswer.setSurvey(surveyEx);
                        surveyAnswer.setQuestion(question);
                        surveyAnswer.setSelectedAnswer(answer);
                        surveyAnswers.add(surveyAnswer);
                    });
        }

        surveyEx.setSurveyAnswers(surveyAnswers);

        Map<Answer, Boolean> surveyMap = surveyAnswers.stream()
                .collect(Collectors.toMap(surveyAnswer -> surveyAnswer.getSelectedAnswer(), surveyAnswer -> true));

        final AtomicInteger rightQuestions = new AtomicInteger();
        task.getQuestions().stream().forEach(question -> {
            if (question.getAnswers().stream()
                    .filter(answer -> (answer.isRight() && !surveyMap.containsKey(answer)) || (!answer.isRight() && surveyMap.containsKey(answer)))
                    .count() == 0) {
                rightQuestions.incrementAndGet();
            }
        });

        surveyEx.setScore((int)((rightQuestions.get() * 100.0f) / task.getQuestions().size()));

        return surveyDAO.save(surveyEx);
    }
}
