package com.example.codingChallenge.Services;

import com.example.codingChallenge.Exceptions.TaskNotFoundException;
import com.example.codingChallenge.Reposoitories.TaskRepository;
import com.example.codingChallenge.model.Status;
import com.example.codingChallenge.model.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    @Autowired
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public Task createTask(Task task) {
        return taskRepository.save(task);
    }

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public Task getTaskById(Long id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException(id)); // Throw exception if task not found
    }

    public Task updateTaskStatus(Long id, Status status) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException(id));  // Handle task not found

        task.setStatus(status);  // Set the new status here

        return taskRepository.save(task);  // Save the updated task
    }


    public void deleteTask(Long id) {
        Task task = getTaskById(id);
        taskRepository.delete(task);
    }
}

