package com.example.codingChallenge.Exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // Add SLF4J Logger
    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    // Exception handler for TaskNotFoundException
    @ExceptionHandler(TaskNotFoundException.class)
    public ResponseEntity<String> handleTaskNotFoundException(TaskNotFoundException ex) {
        log.error("Task not found: " + ex.getMessage());  // Log the error message
        return new ResponseEntity<>("Task not found: " + ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    // Handle other exceptions if necessary
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGenericException(Exception ex) {
        log.error("An error occurred: " + ex.getMessage());  // Log the error message
        return new ResponseEntity<>("An error occurred: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // Handle invalid enum values (e.g., "INVALID_STATUS")
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<String> handleInvalidEnumValue(HttpMessageNotReadableException ex) {
        return new ResponseEntity<>("Invalid status value. Please use 'PENDING', 'IN_PROGRESS', or 'COMPLETED'.", HttpStatus.BAD_REQUEST);
    }
}

