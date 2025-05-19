package ru.tarasov.testing.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.tarasov.testing.aspect.annotation.EntryLoggable;
import ru.tarasov.testing.aspect.annotation.ReturnLoggable;
import ru.tarasov.testing.aspect.annotation.ThrowingLoggable;
import ru.tarasov.testing.aspect.annotation.TimeExecutionLoggable;
import ru.tarasov.testing.dto.TaskDto;
import ru.tarasov.testing.dto.TaskRequestDto;
import ru.tarasov.testing.dto.TaskUpdateDto;
import ru.tarasov.testing.exception.TaskNotFoundException;
import ru.tarasov.testing.kafka.KafkaTaskProducer;
import ru.tarasov.testing.mapper.TaskMapper;
import ru.tarasov.testing.model.Task;
import ru.tarasov.testing.repository.TaskRepository;
import ru.tarasov.testing.service.TaskService;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;

    private final TaskMapper taskMapper;

    private final KafkaTaskProducer kafkaTaskProducer;

    @Value("${kafka.topic.mail_notification}")
    private String topic;

    @TimeExecutionLoggable
    @Override
    public List<TaskDto> findAll() {
        return taskRepository.findAll().stream()
                .map(taskMapper::taskToTaskDto)
                .toList();
    }

    @EntryLoggable
    @Override
    public TaskDto createTask(TaskRequestDto taskRequestDto) {
        Task taskToSave = taskMapper.taskRequestDtoToTask(taskRequestDto);

        return taskMapper.taskToTaskDto(taskRepository.save(taskToSave));
    }

    @ThrowingLoggable
    @ReturnLoggable
    @Override
    public TaskDto findById(UUID taskId) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new TaskNotFoundException(taskId));

        return taskMapper.taskToTaskDto(task);
    }

    @ThrowingLoggable
    @ReturnLoggable
    @Override
    public TaskDto updateTask(UUID taskId, TaskUpdateDto taskUpdateDto) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new TaskNotFoundException(taskId));

        String status = task.getStatus();
        taskMapper.updateTaskFromTaskUpdateDto(taskUpdateDto, task);
        TaskDto taskDto = taskMapper.taskToTaskDto(taskRepository.save(task));
        if (!taskDto.status().equals(status)) {
            kafkaTaskProducer.sendTo(topic, taskDto);
        }
        return taskDto;
    }

    @ThrowingLoggable
    @Override
    public void deleteTask(UUID taskId) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new TaskNotFoundException(taskId));

        taskRepository.delete(task);
    }
}
