package pl.natekrank.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.MailException;
import org.springframework.mail.MailParseException;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import pl.natekrank.model.Survey;
import pl.natekrank.repository.SurveyDAO;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.List;

@Component("surveyScheduler")
public class SurveyScheduler {
    private static final Logger LOGGER = LoggerFactory.getLogger(SurveyScheduler.class);

    @Autowired
    private SurveyDAO surveyDAO;
    @Autowired
    private MailSender mailSender;
    @Autowired
    private Environment environment;

    public void processSurveys() {
        List<Survey> surveys = surveyDAO.getQueueSurveys();
        for (Survey survey : surveys) {
            try {
                SimpleMailMessage message = new SimpleMailMessage();
                message.setFrom(survey.getSender());
                message.setTo(survey.getEmail());
                message.setSubject("Test");
                message.setText(createMessageWithLink(survey));

                mailSender.send(message);

                survey.setSent(true);
                surveyDAO.save(survey);
            } catch (MailException exception) {
                LOGGER.error(exception.getMessage());
            }
        }
    }

    private String createMessageWithLink(Survey survey) {
        String message = survey.getMessage();

        StringBuilder builder = new StringBuilder();
        builder.append(message)
                .append("\n")
                .append(environment.getProperty("base_url"))
                .append("/survey/" + survey.getSurveyKey());
        return builder.toString();
    }
}
