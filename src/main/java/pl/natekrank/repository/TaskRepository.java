package pl.natekrank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.natekrank.model.Task;

/**
 * Created by ievgenii on 29.09.16.
 */
public interface TaskRepository extends JpaRepository<Task, Long> {
}
