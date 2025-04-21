package com.example.codingChallenge.Exceptions;

import lombok.Getter;

@Getter
public class TaskNotFoundException extends RuntimeException {
    private final String message;

    public TaskNotFoundException(Long taskId) {
        super("Task not found with ID: " + taskId);
        this.message = "Task not found with ID: " + taskId;
    }
}