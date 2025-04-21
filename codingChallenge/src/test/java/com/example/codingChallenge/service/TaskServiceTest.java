package com.example.codingChallenge.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.example.codingChallenge.Services.TaskService;
import com.example.codingChallenge.model.Status;
import com.example.codingChallenge.model.Task;
import com.example.codingChallenge.Reposoitories.TaskRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class TaskServiceTest {

    @Mock
    private TaskRepository taskRepository;

    @InjectMocks
    private TaskService taskService;

    private Task task;

    @BeforeEach
    void setUp() {
        task = new Task(null,"Test Task", "This is a test task", Status.IN_PROGRESS, LocalDateTime.now().plusDays(1));
        task.setId(1L);
    }

    @Test
    void shouldReturnAllTasks() {
        List<Task> tasks = Arrays.asList(task);
        when(taskRepository.findAll()).thenReturn(tasks);

        List<Task> result = taskService.getAllTasks();

        assertEquals(1, result.size());
        verify(taskRepository, times(1)).findAll();
    }

    @Test
    void shouldReturnTaskById() {
        when(taskRepository.findById(1L)).thenReturn(Optional.of(task));

        Task result = taskService.getTaskById(1L);

        assertNotNull(result);
        assertEquals("Test Task", result.getTitle());
        verify(taskRepository, times(1)).findById(1L);
    }

    @Test
    void shouldCreateTask() {
        when(taskRepository.save(any(Task.class))).thenReturn(task);

        Task result = taskService.createTask(task);

        assertNotNull(result);
        assertEquals("Test Task", result.getTitle());
        verify(taskRepository, times(1)).save(task);
    }

    @Test
    void shouldUpdateTaskStatus() {
        when(taskRepository.findById(1L)).thenReturn(Optional.of(task));
        when(taskRepository.save(any(Task.class))).thenReturn(task);

        Task result = taskService.updateTaskStatus(1L, Status.COMPLETED);

        assertNotNull(result);
        assertEquals(Status.COMPLETED, result.getStatus());
        verify(taskRepository, times(1)).findById(1L);
        verify(taskRepository, times(1)).save(task);
    }

    @Test
    void shouldDeleteTask() {
        doNothing().when(taskRepository).deleteById(1L);  // Only stub deleteById

        taskService.deleteTask(1L);

        verify(taskRepository, times(1)).deleteById(1L);
    }

}
