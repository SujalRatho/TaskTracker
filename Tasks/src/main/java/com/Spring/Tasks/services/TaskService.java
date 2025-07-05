package com.Spring.Tasks.services;

import com.Spring.Tasks.domain.entities.Task;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TaskService {
    // list all tasks associated to tasklistid
    List<Task>  listsTasks(UUID taskListId);

    // create new task
    Task createTask(UUID taskListId,Task task);

    // get a specific task
    Optional<Task> getTask(UUID taskListID,UUID taskId);

    //update task including status as completed or not
    Task updateTask(UUID taskListId, UUID taskId, Task task);

}
