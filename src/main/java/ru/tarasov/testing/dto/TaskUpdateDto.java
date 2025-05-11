package ru.tarasov.testing.dto;

public record TaskUpdateDto(
        String title,
        String description,
        Long userId
) {
}
