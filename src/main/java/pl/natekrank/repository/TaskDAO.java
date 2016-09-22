package pl.natekrank.repository;

import pl.natekrank.model.Question;
import pl.natekrank.model.Task;
import pl.natekrank.model.User;

import java.util.List;

public interface TaskDAO {
    List<Task> getAllTasks();
    Task save(Task task);
    Task getTask(Long id);
}
