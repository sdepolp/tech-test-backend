package cl.coopeuch.techtest.application.impl;

import cl.coopeuch.techtest.application.TaskService;
import cl.coopeuch.techtest.domain.Task;
import cl.coopeuch.techtest.infrastructure.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public Task addTask(Task task) {
        task.setFechaCreacion(new Date());
        task.setVigente(true);
        return taskRepository.save(task);
    }

    public void deleteTask(Long taskId) {
        Optional<Task> optionalTask = taskRepository.findById(taskId);
        if (optionalTask.isPresent()) {
            taskRepository.deleteById(taskId);
        } else {
            throw new RuntimeException("No se puede eliminar la tarea porque no existe");
        }
    }

    public Task editTask(Task task) {
        Optional<Task> optionalExistingTask = taskRepository.findById(task.getId());
        if (optionalExistingTask.isPresent()) {
            Task existingTask = optionalExistingTask.get();
            existingTask.setDescripcion(task.getDescripcion());
            existingTask.setFechaCreacion(task.getFechaCreacion());
            existingTask.setVigente(task.isVigente());
            return taskRepository.save(existingTask);
        } else {
            return null;
        }
    }
}

