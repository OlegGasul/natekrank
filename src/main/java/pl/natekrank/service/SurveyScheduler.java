package pl.natekrank.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import pl.natekrank.model.Survey;
import pl.natekrank.repository.SurveyRepository;

import javax.servlet.ServletContext;
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
    private ServletContext servletContext;
    @Value("${mail.url}")
    private String applicationUrl;
    @Value("${mail.subject}")
    private String mailSubject;

    private void processSendMessage() {
        List<Survey> surveys = repository.findBySentIsFalseOrderByIdAsc();
        for (Survey survey : surveys) {
            try {
                SimpleMailMessage message = new SimpleMailMessage();
                message.setFrom(survey.getSender());
                message.setTo(survey.getEmail());
                message.setSubject(mailSubject);
                message.setText(createSendToCandidateMessage(survey));

                mailSender.send(message);

                survey.setSent(true);
                repository.save(survey);
            } catch (Exception exception) {
                LOGGER.error(exception.getMessage());
            }
        }
    }

    private void processNotifyMessage() {
        List<Survey> surveys = repository.findByFinishedIsNotNullAndSenderNotifiedIsNull();

        for (Survey survey : surveys) {
            try {
                SimpleMailMessage message = new SimpleMailMessage();
                message.setFrom(survey.getSender());
                message.setTo(survey.getEmail());
                message.setSubject(mailSubject);
                message.setText(createSenderNotificationMessage(survey));

                mailSender.send(message);

                survey.setSenderNotified(true);
                repository.save(survey);
            } catch (Exception exception) {
                LOGGER.error(exception.getMessage());
            }
        }
    }

    public void processSurveys() {
        processSendMessage();
        processNotifyMessage();
    }

    private String createSendToCandidateMessage(Survey survey) {
        String message = survey.getMessage();

        StringBuilder builder = new StringBuilder();
        builder.append(message)
                .append("\n")
                .append(applicationUrl)
                .append(servletContext.getContextPath())
                .append("/survey/" + survey.getToken());
        return builder.toString();
    }

    private String createSenderNotificationMessage(Survey survey) {
        String message = survey.getMessage();

        StringBuilder builder = new StringBuilder();
        builder.append(message)
                .append("\n")
                .append(applicationUrl)
                .append(servletContext.getContextPath())
                .append("/result/" + survey.getToken());
        return builder.toString();
    }
}
