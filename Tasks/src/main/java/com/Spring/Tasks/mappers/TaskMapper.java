package com.Spring.Tasks.mappers;

import com.Spring.Tasks.domain.dto.TaskDto;
import com.Spring.Tasks.domain.entities.Task;

public interface TaskMapper {
    Task fromDto(TaskDto taskDto);
    TaskDto toDto(Task task);
}
