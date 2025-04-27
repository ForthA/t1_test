package ru.tarasov.testing.service;

import ru.tarasov.testing.dto.TaskDto;
import ru.tarasov.testing.dto.TaskRequestDto;
import ru.tarasov.testing.dto.TaskUpdateDto;

import java.util.List;
import java.util.UUID;

public interface TaskService {

    List<TaskDto> findAll();

    TaskDto createTask(TaskRequestDto taskRequestDto);

    TaskDto findById(UUID taskId);

    TaskDto updateTask(UUID taskId, TaskUpdateDto taskUpdateDto);

    void deleteTask(UUID taskId);

}
