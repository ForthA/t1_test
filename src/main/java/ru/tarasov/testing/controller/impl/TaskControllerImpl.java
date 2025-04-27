package ru.tarasov.testing.controller.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.tarasov.testing.controller.TaskController;
import ru.tarasov.testing.dto.TaskDto;
import ru.tarasov.testing.dto.TaskRequestDto;
import ru.tarasov.testing.dto.TaskUpdateDto;
import ru.tarasov.testing.service.TaskService;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class TaskControllerImpl implements TaskController {

    private final TaskService taskService;

    @Override
    public ResponseEntity<List<TaskDto>> findAll() {
        return new ResponseEntity<>(taskService.findAll(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<TaskDto> findById(UUID id) {
        return new ResponseEntity<>(taskService.findById(id), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<TaskDto> createTask(TaskRequestDto taskRequestDto) {
        return new ResponseEntity<>(taskService.createTask(taskRequestDto), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Void> deleteTask(UUID id) {
        taskService.deleteTask(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Override
    public ResponseEntity<TaskDto> updateTask(UUID id, TaskUpdateDto taskUpdateDto) {
        return new ResponseEntity<>(taskService.updateTask(id, taskUpdateDto), HttpStatus.ACCEPTED);
    }
}
