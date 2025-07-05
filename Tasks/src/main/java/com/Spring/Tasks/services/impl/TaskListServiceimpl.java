package com.Spring.Tasks.services.impl;

import com.Spring.Tasks.domain.entities.TaskList;
import com.Spring.Tasks.repositories.TaskListRepository;
import com.Spring.Tasks.services.TaskListService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
public class TaskListServiceimpl implements TaskListService {

    private final TaskListRepository taskListRepository;

    public TaskListServiceimpl(TaskListRepository taskListRepository) {
        this.taskListRepository = taskListRepository;
    }

    @Override
    public List<TaskList> listTaskLists() {
        return taskListRepository.findAll();
    }

    @Override
    public TaskList createTaskList(TaskList taskList) {
        //if already exists
        if(null!=taskList.getId()){
            throw  new IllegalArgumentException("Task list already has an ID!");
        }
        // Title must be present
        if(null==taskList.getTitle() || taskList.getTitle().isBlank()){
            throw  new IllegalArgumentException("Task list title must be present!");
        }

        LocalDateTime now  = LocalDateTime.now();
        return taskListRepository.save(new TaskList(
                null,
                taskList.getTitle(),
                taskList.getDescription(),
                null,
                now,
                now
        ));

    }

    @Override
    public Optional<TaskList> getTaskList(UUID id) {
        return taskListRepository.findById(id);
    }

    @Override
    public TaskList updateTaskList(UUID taskListId, TaskList taskList) {
        if(null ==taskList.getId()){
            throw new IllegalArgumentException("Task list must have an ID");
        }
        if(!Objects.equals(taskListId,taskList.getId())){
            throw new IllegalArgumentException("Attempting to change task list ID, this is not allowed!");
        }
        TaskList existingTaskList = taskListRepository.findById(taskListId).orElseThrow(()-> new IllegalArgumentException("Task list not found!"));
        existingTaskList.setTitle(taskList.getTitle());
        existingTaskList.setDescription(taskList.getDescription());
        existingTaskList.setUpdated(LocalDateTime.now());
        return taskListRepository.save(existingTaskList);

    }

    @Override
    public void deleteTaskList(UUID taskListId) {
        // we are not checking if the tasklist exists or not bcoz jpa deletebyId method handles non-existing entities
        taskListRepository.deleteById(taskListId);
    }

}