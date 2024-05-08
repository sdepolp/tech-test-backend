package cl.coopeuch.techtest.application;

import cl.coopeuch.techtest.domain.Task;

import java.util.List;

public interface TaskService {
    List<Task> getAllTasks();
    Task addTask(Task task);
    void deleteTask(Long taskId);
    Task editTask(Task task);
}
