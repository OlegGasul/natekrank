package pl.natekrank.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.natekrank.model.Task;
import pl.natekrank.repository.TaskRepository;

import java.util.Date;
import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {
    private static final Logger LOGGER = LoggerFactory.getLogger(TaskServiceImpl.class);

    @Autowired
    private TaskRepository repository;

    @Override
    public List<Task> getAllTasks() {
        return repository.findAll();
    }

    @Override
    public Task save(Task task) {
        Date modified = new Date();
        if (task.getCreated() == null) {
            task.setCreated(modified);
        }
        task.setModified(modified);

        return repository.save(task);
    }

    @Override
    public Task getTaskById(Long id) {
        return repository.findOne(id);
    }
}
