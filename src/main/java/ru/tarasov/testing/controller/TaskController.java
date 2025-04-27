package ru.tarasov.testing.controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.tarasov.testing.dto.TaskDto;
import ru.tarasov.testing.dto.TaskRequestDto;
import ru.tarasov.testing.dto.TaskUpdateDto;

import java.util.List;
import java.util.UUID;

@RequestMapping("/tasks")
public interface TaskController {

    @GetMapping
    ResponseEntity<List<TaskDto>> findAll();

    @GetMapping("/{id}")
    ResponseEntity<TaskDto> findById(@PathVariable UUID id);

    @PostMapping
    ResponseEntity<TaskDto> createTask(@RequestBody @Valid TaskRequestDto taskRequestDto);

    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteTask(@PathVariable UUID id);

    @PutMapping("/{id}")
    ResponseEntity<TaskDto> updateTask(@PathVariable UUID id, @RequestBody TaskUpdateDto taskUpdateDto);

}
