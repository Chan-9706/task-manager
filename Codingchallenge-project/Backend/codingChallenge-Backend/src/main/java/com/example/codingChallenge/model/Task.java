package com.example.codingChallenge.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

@Entity
//tried using lombok to generate getter and setter methods but I was constsntly running into errors and it was not generating any
//getters or setters. so, had to write the entire code for getters and setters manually.
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Title is required")
    private String title;

    private String description;

    @NotNull(message = "Status cannot be null")
    @Enumerated(EnumType.STRING)
    private Status status;

    @Future(message = "Due date must be in the future")
    private LocalDateTime dueDate;

    // No-args constructor
    public Task() {
    }

    // All-args constructor
    public Task(Long id, String title, String description, Status status, LocalDateTime dueDate) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.status = status;
        this.dueDate = dueDate;
    }

    // Getter for id
    public Long getId() {
        return id;
    }

    // Setter for id
    public void setId(Long id) {
        this.id = id;
    }

    // Getter for title
    public String getTitle() {
        return title;
    }

    // Setter for title
    public void setTitle(String title) {
        this.title = title;
    }

    // Getter for description
    public String getDescription() {
        return description;
    }

    // Setter for description
    public void setDescription(String description) {
        this.description = description;
    }

    // Getter for status
    public Status getStatus() {
        return status;
    }

    // Setter for status
    public void setStatus(Status status) {
        this.status = status;
    }

    // Getter for dueDate
    public LocalDateTime getDueDate() {
        return dueDate;
    }

    // Setter for dueDate
    public void setDueDate(LocalDateTime dueDate) {
        this.dueDate = dueDate;
    }

    // Override toString (if needed)
    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", status=" + status +
                ", dueDate=" + dueDate +
                '}';
    }
}



