package pl.natekrank.service;

import pl.natekrank.model.Task;
import pl.natekrank.repository.TaskDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {
    private static final Logger LOGGER = LoggerFactory.getLogger(TaskServiceImpl.class);

    @Autowired
    private TaskDAO taskDAO;

    @Override
    public List<Task> getAllTasks() {
        return taskDAO.getAllTasks();
    }

    @Override
    public Task save(Task task) {
        Date modified = new Date();
        if (task.getCreated() == null) {
            task.setCreated(modified);
        }
        task.setModified(modified);

        return taskDAO.save(task);
    }

    @Override
    public Task getTaskById(Long id) {
        return taskDAO.getTask(id);
    }
}
