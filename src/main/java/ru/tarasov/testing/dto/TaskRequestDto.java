package ru.tarasov.testing.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;

@Builder
public record TaskRequestDto(
        @NotNull(message = "title is required")
        @Size(min = 3, max = 255, message = "The title must contain from 3 to 255 characters")
        String title,
        String description,
        @NotNull(message = "userId is required")
        Long userId,
        @NotNull(message = "task status should not be null")
        String status
) {
}
