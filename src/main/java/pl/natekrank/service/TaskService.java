package pl.natekrank.service;

import pl.natekrank.model.Task;
import pl.natekrank.model.User;

import java.util.List;

public interface TaskService {
    List<Task> getAllTasks();
    Task save(Task task);
    Task getTaskById(Long id);
}