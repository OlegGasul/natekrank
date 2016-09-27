package pl.natekrank.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.stereotype.Component;
import pl.natekrank.model.Survey;
import pl.natekrank.repository.SurveyDAO;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

import java.util.List;

@Component("surveyScheduler")
public class SurveyScheduler {
    private static final Logger LOGGER = LoggerFactory.getLogger(SurveyScheduler.class);

    @Autowired
    private SurveyDAO surveyDAO;
    @Autowired
    private MailSender mailSender;

    public void processSurveys() {
        if (0 == 0)
            return;

        List<Survey> surveys = surveyDAO.getQueueSurveys();
        for (Survey survey : surveys) {
            try {
                SimpleMailMessage message = new SimpleMailMessage();
                message.setFrom(survey.getSender());
                message.setTo(survey.getEmail());
                message.setSubject("Test");
                message.setText(survey.getMessage());
                mailSender.send(message);

                survey.setSent(true);
                surveyDAO.save(survey);
            } catch (MailException exception) {
                LOGGER.error(exception.getMessage());
            }
        }
    }
}
