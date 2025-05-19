package ru.tarasov.testing.dto;

import java.util.UUID;

public record TaskDto(
        UUID id,
        String title,
        String description,
        Long userId,
        String status
) {
}
