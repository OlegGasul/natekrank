package pl.natekrank.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.natekrank.helpers.SurveyHelper;
import pl.natekrank.model.*;
import pl.natekrank.model.dto.QuestionDto;
import pl.natekrank.model.dto.SurveyDto;
import pl.natekrank.model.dto.TaskDto;
import pl.natekrank.repository.SurveyRepository;
import pl.natekrank.repository.TaskRepository;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Service
@Transactional
public class SurveyServiceImpl implements SurveyService {
    @Autowired
    private SurveyRepository surveyRepository;

    @Autowired
    private TaskRepository taskRepository;

    @Override
    public Survey save(Survey survey) {
        if (survey.getToken() == null) {
            survey.setToken(SurveyHelper.generateToken());
        }

        Task task = taskRepository.getOne(survey.getTaskId());
        survey.setTask(task);

        return surveyRepository.saveAndFlush(survey);
    }

    @Override
    public List<Survey> getAllSurveys() {
        return surveyRepository.findAll();
    }

    @Override
    public Survey getSurveyById(Long id) {
        return surveyRepository.findOne(id);
    }

    @Override
    public Survey getSurveyByToken(String token) {
        return surveyRepository.findByToken(token);
    }

    @Override
    public Survey startSurvey(Survey survey) {
        if (survey.getStarted() == null) {
            survey.setStarted(new Date());
            survey = surveyRepository.saveAndFlush(survey);
        }

        return survey;
    }

    @Override
    public Survey submitSurvey(SurveyDto surveyDto) {
        Survey survey = surveyRepository.findByToken(surveyDto.getToken());
        Task task = survey.getTask();

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
                        surveyAnswer.setSurvey(survey);
                        surveyAnswer.setQuestion(question);
                        surveyAnswer.setSelectedAnswer(answer);
                        surveyAnswers.add(surveyAnswer);
                    });
        }

        survey.setSurveyAnswers(surveyAnswers);

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

        survey.setScore((int)((rightQuestions.get() * 100.0f) / task.getQuestions().size()));

        return surveyRepository.saveAndFlush(survey);
    }
}
