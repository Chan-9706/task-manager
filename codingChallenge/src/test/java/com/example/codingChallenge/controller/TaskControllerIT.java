package com.example.codingChallenge.controller;

import com.example.codingChallenge.model.Status;
import com.example.codingChallenge.model.Task;
import com.example.codingChallenge.Reposoitories.TaskRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@ActiveProfiles("test")  // Ensure you are using a test profile (e.g., different DB, etc.)
public class TaskControllerIT {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private TaskRepository taskRepository;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        // Initialize MockMvc object to perform requests
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

        // Clear the repository before each test to ensure isolation
        taskRepository.deleteAll();
    }

    @Test
    public void shouldCreateTask() throws Exception {
        // Prepare the task object to send in the request
        Task newTask = new Task(null, "Test Task", "Test Description", Status.PENDING, null);

        // Perform POST request to create a task
        mockMvc.perform(post("/api/tasks")
                        .contentType("application/json")
                        .content("{\"title\":\"Test Task\",\"description\":\"Test Description\",\"status\":\"PENDING\"}"))
                .andExpect(status().isCreated())  // Expect HTTP status 201 (Created)
                .andExpect(jsonPath("$.title").value("Test Task"))
                .andExpect(jsonPath("$.description").value("Test Description"))
                .andExpect(jsonPath("$.status").value("PENDING"));
    }

    @Test
    public void shouldGetAllTasks() throws Exception {
        // Add a task to the repository
        taskRepository.save(new Task(null,"Task 1", "Description 1", Status.PENDING, null));

        // Perform GET request to fetch all tasks
        mockMvc.perform(get("/api/tasks"))
                .andExpect(status().isOk())  // Expect HTTP status 200 (OK)
                .andExpect(jsonPath("$[0].title").value("Task 1"))
                .andExpect(jsonPath("$[0].description").value("Description 1"))
                .andExpect(jsonPath("$[0].status").value("PENDING"));
    }

    @Test
    public void shouldGetTaskById() throws Exception {
        // Save a task
        Task task = taskRepository.save(new Task(null,"Task 1", "Description 1", Status.PENDING, null));

        // Perform GET request to fetch the task by ID
        mockMvc.perform(get("/api/tasks/{id}", task.getId()))
                .andExpect(status().isOk())  // Expect HTTP status 200 (OK)
                .andExpect(jsonPath("$.title").value("Task 1"))
                .andExpect(jsonPath("$.description").value("Description 1"))
                .andExpect(jsonPath("$.status").value("PENDING"));
    }

    @Test
    public void shouldUpdateTaskStatus() throws Exception {
        // Save a task
        Task task = taskRepository.save(new Task(null,"Task 1", "Description 1", Status.PENDING, null));

        // Perform PATCH request to update task status
        mockMvc.perform(patch("/api/tasks/{id}/status", task.getId())
                        .param("status", "COMPLETED"))
                .andExpect(status().isOk())  // Expect HTTP status 200 (OK)
                .andExpect(jsonPath("$.status").value("COMPLETED"));
    }

    @Test
    public void shouldDeleteTask() throws Exception {
        // Save a task
        Task task = taskRepository.save(new Task(null,"Task 1", "Description 1", Status.PENDING, null));

        // Perform DELETE request to delete the task
        mockMvc.perform(delete("/api/tasks/{id}", task.getId()))
                .andExpect(status().isNoContent());  // Expect HTTP status 204 (No Content)

        // Verify task has been deleted
        mockMvc.perform(get("/api/tasks/{id}", task.getId()))
                .andExpect(status().isNotFound());  // Expect HTTP status 404 (Not Found)
    }
}


