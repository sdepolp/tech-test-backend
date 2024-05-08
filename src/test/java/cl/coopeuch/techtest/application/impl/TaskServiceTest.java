package cl.coopeuch.techtest.application.impl;

import cl.coopeuch.techtest.application.TaskService;
import cl.coopeuch.techtest.domain.Task;
import cl.coopeuch.techtest.infrastructure.repository.TaskRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class TaskServiceTest {

    @Mock
    private TaskRepository taskRepository;

    @InjectMocks
    private TaskServiceImpl taskService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAddTask() {

        Task newTask = new Task();
        newTask.setDescripcion("Tarea de prueba");
        newTask.setFechaCreacion(new Date());
        newTask.setVigente(true);
        when(taskRepository.save(any(Task.class))).thenReturn(newTask);
        Task addedTask = taskService.addTask(newTask);
        verify(taskRepository, times(1)).save(any(Task.class));
        assertEquals("Tarea de prueba", addedTask.getDescripcion());
        assertEquals(true, addedTask.isVigente());
    }

    @Test
    public void testGetAllTasksEmptyList() {
        when(taskRepository.findAll()).thenReturn(Collections.emptyList());
        List<Task> tasks = taskService.getAllTasks();
        assertEquals(0, tasks.size());
    }

    @Test
    public void testGetAllTasksWithTasks() {
        List<Task> tasks = List.of(
                new Task(1L, "Tarea 1", new Date(), true),
                new Task(2L, "Tarea 2", new Date(), false)
        );

        when(taskRepository.findAll()).thenReturn(tasks);

        List<Task> retrievedTasks = taskService.getAllTasks();

        assertEquals(tasks.size(), retrievedTasks.size());
        assertEquals(tasks, retrievedTasks);
    }

    @Test
    public void testEditExistingTask() {

        Task existingTask = new Task(1L, "Tarea existente", new Date(), true);

        when(taskRepository.findById(1L)).thenReturn(Optional.of(existingTask));
        when(taskRepository.save(any(Task.class))).thenReturn(existingTask);
        Task updatedTask = new Task(1L, "Tarea actualizada", new Date(), false);
        Task editedTask = taskService.editTask(updatedTask);
        verify(taskRepository, times(1)).findById(1L);
        verify(taskRepository, times(1)).save(updatedTask);

        assertNotNull(editedTask);
        assertEquals(updatedTask.getId(), editedTask.getId());
        assertEquals(updatedTask.getDescripcion(), editedTask.getDescripcion());
        assertEquals(updatedTask.isVigente(), editedTask.isVigente());
    }

    @Test
    public void testEditNonExistingTask() {
        when(taskRepository.findById(1L)).thenReturn(Optional.empty());
        Task updatedTask = new Task(1L, "Tarea actualizada", new Date(), false);
        Task editedTask = taskService.editTask(updatedTask);
        verify(taskRepository, times(1)).findById(1L);
        assertNull(editedTask);
    }

    @Test
    public void testDeleteNonExistingTask() {
        when(taskRepository.findById(1L)).thenReturn(Optional.empty());

        // Verificar que se lance RuntimeException al intentar eliminar una tarea que no existe
        assertThrows(RuntimeException.class, () -> taskService.deleteTask(1L),
                "Se esperaba que se lanzara una RuntimeException");

        verify(taskRepository, times(1)).findById(1L);
        verify(taskRepository, never()).delete(any(Task.class));
    }

}
