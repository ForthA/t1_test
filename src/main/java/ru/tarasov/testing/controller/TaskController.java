package ru.tarasov.testing.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import ru.tarasov.testing.dto.TaskDto;
import ru.tarasov.testing.dto.TaskRequestDto;
import ru.tarasov.testing.dto.TaskUpdateDto;

import java.util.List;
import java.util.UUID;

@RequestMapping("/tasks")
public interface TaskController {

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    List<TaskDto> findAll();

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    TaskDto findById(@PathVariable UUID id);

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    TaskDto createTask(@RequestBody @Valid TaskRequestDto taskRequestDto);

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteTask(@PathVariable UUID id);

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    TaskDto updateTask(@PathVariable UUID id, @RequestBody TaskUpdateDto taskUpdateDto);

}
