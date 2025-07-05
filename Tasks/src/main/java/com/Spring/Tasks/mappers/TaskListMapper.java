package com.Spring.Tasks.mappers;

import com.Spring.Tasks.domain.dto.TaskListDto;
import com.Spring.Tasks.domain.entities.TaskList;

public interface TaskListMapper {
     TaskList fromDto(TaskListDto taskListDto);

    TaskListDto toDto(TaskList taskList);
}
