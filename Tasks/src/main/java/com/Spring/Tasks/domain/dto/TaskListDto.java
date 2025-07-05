package com.Spring.Tasks.domain.dto;

import java.util.List;
import java.util.UUID;

public record TaskListDto(
        UUID id,
        String title,
        String description,
        Integer count,
        Double Progress,
        List<TaskDto> tasks
) {
}
