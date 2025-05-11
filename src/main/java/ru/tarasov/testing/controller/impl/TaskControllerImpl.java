package ru.tarasov.testing.controller.impl;

import lombok.RequiredArgsConstructor;
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
    public List<TaskDto> findAll() {
        return taskService.findAll();
    }

    @Override
    public TaskDto findById(UUID id) {
        return taskService.findById(id);
    }

    @Override
    public TaskDto createTask(TaskRequestDto taskRequestDto) {
        return taskService.createTask(taskRequestDto);
    }

    @Override
    public void deleteTask(UUID id) {
        taskService.deleteTask(id);
    }

    @Override
    public TaskDto updateTask(UUID id, TaskUpdateDto taskUpdateDto) {
        return taskService.updateTask(id, taskUpdateDto);
    }
}
