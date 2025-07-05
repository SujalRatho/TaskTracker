package com.Spring.Tasks.domain.dto;

import com.Spring.Tasks.domain.entities.TaskPriority;
import com.Spring.Tasks.domain.entities.TaskStatus;

import java.time.LocalDateTime;
import java.util.UUID;

public record TaskDto(
        UUID id,
        String title,
        String description,
        LocalDateTime dueDate,
        TaskPriority priority,
        TaskStatus status
) {
}
