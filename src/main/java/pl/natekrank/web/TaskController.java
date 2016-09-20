package pl.natekrank.web;

import org.springframework.web.bind.annotation.*;
import pl.natekrank.model.Task;
import pl.natekrank.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.List;

@RestController
@RequestMapping("/task")
public class TaskController {
    @Autowired
    private TaskService taskService;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Task> save(@RequestBody Task task) {
        return new ResponseEntity<>(taskService.save(task), HttpStatus.OK);
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ResponseEntity<List<Task>> list() {
        return new ResponseEntity<>(taskService.getAllTasks(), HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Task> getTask(@PathVariable Long id) {
        return new ResponseEntity<>(taskService.getTask(id), HttpStatus.OK);
    }
}
