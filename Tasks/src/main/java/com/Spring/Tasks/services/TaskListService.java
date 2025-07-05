package com.Spring.Tasks.services;

import com.Spring.Tasks.domain.entities.TaskList;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TaskListService {
    // shows all task lists at home
    List<TaskList> listTaskLists();
    // creates a new tasklist
    TaskList createTaskList(TaskList taskList);
    // searches a tasklist
    Optional<TaskList> getTaskList(UUID id);
    //update a tasklist     // full update bcoz changes done in db also
    TaskList updateTaskList(UUID taskListId,TaskList taskList);

    void deleteTaskList(UUID taskListId);
}
