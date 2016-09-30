package pl.natekrank.repository;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import pl.natekrank.model.Survey;
import pl.natekrank.model.User;
import pl.natekrank.model.builder.SurveyBuilder;
import pl.natekrank.model.builder.UserBuilder;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.unitils.reflectionassert.ReflectionAssert.assertReflectionEquals;
import static pl.natekrank.model.builder.ModelBuilderFactory.getBuilder;

/**
 * Created by ievgenii on 22.09.16.
 */
public class SurveyRepositoryTest extends BaseRepositoryTest {

    @Autowired
    SurveyRepository repository;


    @Test
    @DatabaseSetup("classpath:data/surveys.xml")
    public void shouldFindAllSurveys() throws Exception {
        //given
        List<Survey> expected = Arrays.asList(getBuilder(SurveyBuilder.class)
                        .withId(0)
                        .withEmail("user@user.com")
                        .withSender("Guest")
                        .withMessage("Test")
                        .withToken("T0")
                        .withSent(false)
                        .withSenderNotified(false)
                        .withMinutesForSolving(120)
                        .build(),
                getBuilder(SurveyBuilder.class)
                        .withId(1)
                        .withEmail("user1@user.com")
                        .withSender("Guest1")
                        .withMessage("Test")
                        .withToken("T1")
                        .withSent(true)
                        .withSenderNotified(false)
                        .withMinutesForSolving(120)
                        .build(),
                getBuilder(SurveyBuilder.class)
                        .withId(2)
                        .withEmail("user2@user.com")
                        .withSender("Guest2")
                        .withMessage("Test")
                        .withToken("T2")
                        .withSent(false)
                        .withSenderNotified(false)
                        .withMinutesForSolving(120)
                        .build(),
                getBuilder(SurveyBuilder.class)
                        .withId(3)
                        .withEmail("user3@user.com")
                        .withSender("Guest3")
                        .withMessage("Test")
                        .withToken("T3")
                        .withSent(true)
                        .withSenderNotified(false)
                        .withMinutesForSolving(120)
                        .build(),
                getBuilder(SurveyBuilder.class)
                        .withId(4)
                        .withEmail("user4@user.com")
                        .withSender("Guest4")
                        .withMessage("Test")
                        .withToken("T4")
                        .withSent(false)
                        .withSenderNotified(false)
                        .withMinutesForSolving(120)
                        .build()

        );

        //when
        List<Survey> actual = repository.findAll();
        //then
        assertReflectionEquals(expected, actual);

    }

    @Test
    @DatabaseSetup("classpath:data/surveys.xml")
    public void shouldFindByToken() throws Exception {
        //given
        Survey expected = getBuilder(SurveyBuilder.class)
                .withId(0)
                .withToken("T0")
                .withEmail("user@user.com")
                .withSender("Guest")
                .withMessage("Test")
                .withSent(false)
                .withSenderNotified(false)
                .withMinutesForSolving(120)
//                .withAnswers(Collections.EMPTY_LIST)
                .build();

        //when
        Survey actual = repository.findByToken("T0");
        //then
        assertReflectionEquals(expected, actual);

    }

    @Test
    @DatabaseSetup("classpath:data/surveys.xml")
    public void shouldFindNotSentSurveys() throws Exception {
        //given
        List<Survey> expected = Arrays.asList(getBuilder(SurveyBuilder.class)
                        .withId(0)
                        .withEmail("user@user.com")
                        .withSender("Guest")
                        .withMessage("Test")
                        .withToken("T0")
                        .withSent(false)
                        .withSenderNotified(false)
                        .withMinutesForSolving(120)
                        .build(),
                getBuilder(SurveyBuilder.class)
                        .withId(2)
                        .withEmail("user2@user.com")
                        .withSender("Guest2")
                        .withMessage("Test")
                        .withToken("T2")
                        .withSent(false)
                        .withSenderNotified(false)
                        .withMinutesForSolving(120)
                        .build(),
                getBuilder(SurveyBuilder.class)
                        .withId(4)
                        .withEmail("user4@user.com")
                        .withSender("Guest4")
                        .withMessage("Test")
                        .withToken("T4")
                        .withSent(false)
                        .withSenderNotified(false)
                        .withMinutesForSolving(120)
                        .build()

        );

        //when
        List<Survey> actual = repository.findBySentIsFalseOrderByIdAsc();
        //then

        assertReflectionEquals(expected, actual);

    }
}
