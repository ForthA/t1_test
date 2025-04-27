package ru.tarasov.testing.exception;

import java.util.UUID;

public class TaskNotFoundException extends RuntimeException{

    public TaskNotFoundException(UUID taskId){
        super("No task with id " + taskId);
    }

    public TaskNotFoundException(String message){
        super(message);
    }
}
