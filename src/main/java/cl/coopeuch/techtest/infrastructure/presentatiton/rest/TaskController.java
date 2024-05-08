package cl.coopeuch.techtest.infrastructure.presentatiton.rest;

import cl.coopeuch.techtest.application.TaskService;
import cl.coopeuch.techtest.domain.Task;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<List<Task>> getAllTasks() {
        List<Task> tasks = taskService.getAllTasks();
        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }

    @PostMapping
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<Task> addTask(@RequestBody Task task) {
        Task addedTask = taskService.addTask(task);
        return new ResponseEntity<>(addedTask, HttpStatus.CREATED);
    }

    @PutMapping("/{taskId}")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<Task> editTask(@PathVariable Long taskId, @RequestBody Task task) {
        if (!taskId.equals(task.getId())) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Task editedTask = taskService.editTask(task);
        return new ResponseEntity<>(editedTask, HttpStatus.OK);
    }

    @DeleteMapping("/{taskId}")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<Void> deleteTask(@PathVariable Long taskId) {
        taskService.deleteTask(taskId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

