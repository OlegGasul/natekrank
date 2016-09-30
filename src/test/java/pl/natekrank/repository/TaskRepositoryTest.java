package pl.natekrank.repository;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import pl.natekrank.model.Answer;
import pl.natekrank.model.Question;
import pl.natekrank.model.Task;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by ievgenii on 29.09.16.
 */
public class TaskRepositoryTest extends BaseRepositoryTest {

    @Autowired
    TaskRepository repository;

    @Test
    @Ignore
    @DatabaseSetup("classpath:data/tasks.xml")
    @ExpectedDatabase("classpath:data/expected_tasks.xml")
    public void shouldFindAllTasks() {

        Task task = repository.findOne(0L);
        assertThat(task.getName()).isEqualTo("T0");
        List<Question> questions = task.getQuestions();
        assertThat(questions).isNotNull();
        List<Answer> answers = questions.get(0).getAnswers();
        assertThat(answers).isNotNull();

    }

}
