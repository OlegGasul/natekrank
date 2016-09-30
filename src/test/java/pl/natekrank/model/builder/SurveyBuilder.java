package pl.natekrank.model.builder;

import pl.natekrank.model.Answer;
import pl.natekrank.model.Survey;

import java.util.List;

/**
 * Created by ievgenii on 26.09.16.
 */
public class SurveyBuilder implements Builder<Survey> {
    long id;
    private String email;
    private String sender;
    private String message;
    private int minutesForSolving;
    private boolean sent;
    private boolean senderNotified;
    private String token;

    public SurveyBuilder withId(long id) {
        this.id = id;
        return this;
    }
    public SurveyBuilder withEmail(String email) {
        this.email = email;
        return this;
    }
    public SurveyBuilder withSender(String sender) {
        this.sender = sender;
        return this;
    }
    public SurveyBuilder withToken(String token) {
        this.token = token;
        return this;
    }
    public SurveyBuilder withMessage(String message) {
        this.message = message;
        return this;
    }
    public SurveyBuilder withMinutesForSolving(int minutesForSolving) {
        this.minutesForSolving = minutesForSolving;
        return this;
    }
    public SurveyBuilder withSent(boolean sent) {
        this.sent = sent;
        return this;
    }
    public SurveyBuilder withSenderNotified(boolean senderNotified) {
        this.senderNotified = senderNotified;
        return this;
    }
    public SurveyBuilder withAnswers(List<Answer> answers) {
        return this;
    }

    @Override
    public Survey build(){
        Survey survey = new Survey();
        survey.setId(id);
        survey.setEmail(email);
        survey.setMessage(message);
        survey.setMinutesForSolving(minutesForSolving);
        survey.setSender(sender);
        survey.setSent(sent);
        survey.setSenderNotified(senderNotified);
        survey.setToken(token);
        return survey;
    }


}
