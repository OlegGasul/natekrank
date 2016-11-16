package pl.natekrank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.natekrank.model.Survey;
import pl.natekrank.model.Task;

import java.util.List;

public interface SurveyRepository extends JpaRepository<Survey, Long> {
    List<Survey> findBySentIsFalseOrderByIdAsc();
    List<Survey> findByFinishedIsNotNullAndSenderNotifiedIsNull();
    Survey findByToken(String token);
    List<Survey> findByTask(Task task);
}
