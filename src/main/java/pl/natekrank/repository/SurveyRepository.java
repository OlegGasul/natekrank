package pl.natekrank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.natekrank.model.Survey;

import java.util.List;

public interface SurveyRepository extends JpaRepository<Survey, Long> {

    List<Survey> findBySentIsFalseOrderByIdAsc();

    Survey findByToken(String token);
}
