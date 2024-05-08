package cl.coopeuch.techtest.infrastructure.presentation.rest;



import cl.coopeuch.techtest.application.TaskService;
import cl.coopeuch.techtest.application.impl.TaskServiceImpl;
import cl.coopeuch.techtest.domain.Task;
import cl.coopeuch.techtest.infrastructure.presentatiton.rest.TaskController;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.StdDateFormat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(TaskController.class)
@AutoConfigureMockMvc
public class TaskControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TaskServiceImpl taskService;

    @Autowired
    private ObjectMapper objectMapper;


    private Task task;

    @BeforeEach
    void setUp() {
        task = new Task();
        task.setId(1L);
        task.setDescripcion("Tarea de prueba");
        task.setFechaCreacion(new Date());
        task.setVigente(true);
    }

    @Test
    void getAllTasks() throws Exception {
        List<Task> tasks = new ArrayList<>();
        tasks.add(task);

        when(taskService.getAllTasks()).thenReturn(tasks);

        mockMvc.perform(MockMvcRequestBuilders.get("/tasks")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(task.getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].descripcion").value(task.getDescripcion()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].fechaCreacion").value(new StdDateFormat().format(task.getFechaCreacion())))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].vigente").value(task.isVigente()));
    }

    @Test
    void addTask() throws Exception {
        when(taskService.addTask(task)).thenReturn(task);

        mockMvc.perform(MockMvcRequestBuilders.post("/tasks")
                        .content(objectMapper.writeValueAsString(task))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(task.getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.descripcion").value(task.getDescripcion()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.fechaCreacion").value(new StdDateFormat().format(task.getFechaCreacion())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.vigente").value(task.isVigente()));
    }

}