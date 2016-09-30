package pl.natekrank.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import pl.natekrank.model.Survey;
import pl.natekrank.repository.SurveyRepository;

import java.util.List;

@Component("surveyScheduler")
@Transactional
public class SurveyScheduler {
    private static final Logger LOGGER = LoggerFactory.getLogger(SurveyScheduler.class);

    @Autowired
    private SurveyRepository repository;
    @Autowired
    private MailSender mailSender;
    @Autowired
    private Environment environment;

    public void processSurveys() {
        List<Survey> surveys = repository.findBySentIsFalseOrderByIdAsc();
        for (Survey survey : surveys) {
            try {
                SimpleMailMessage message = new SimpleMailMessage();
                message.setFrom(survey.getSender());
                message.setTo(survey.getEmail());
                message.setSubject("Test");
                message.setText(createMessageWithLink(survey));

                mailSender.send(message);

                survey.setSent(true);
                repository.save(survey);
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
                .append("/survey/" + survey.getToken());
        return builder.toString();
    }
}
