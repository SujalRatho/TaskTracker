package com.Spring.Tasks.controllers;

import com.Spring.Tasks.domain.dto.TaskDto;
import com.Spring.Tasks.domain.entities.Task;
import com.Spring.Tasks.mappers.TaskMapper;
import com.Spring.Tasks.services.TaskService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(path = "api/task-lists/{task_list_id}/tasks")
public class TasksController {

    private final TaskService taskService;
    private final TaskMapper taskMapper;

    public TasksController(TaskService taskService, TaskMapper taskMapper) {
        this.taskService = taskService;
        this.taskMapper = taskMapper;
    }

    @GetMapping
    public List<TaskDto> listTasks(@PathVariable("task_list_id")UUID tasklistID){
        return taskService.listsTasks(tasklistID)
                .stream()
                .map(taskMapper::toDto)
                .toList();
    }

    @PostMapping
    public TaskDto createTask(@PathVariable("task_list_id") UUID id,@RequestBody TaskDto taskDto){
        Task createdtask = taskService.createTask(id,taskMapper.fromDto(taskDto));
        return taskMapper.toDto(createdtask);
    }

    @GetMapping(path = "/{task_id}")
    public Optional<TaskDto> getTask(@PathVariable("task_list_id") UUID taskListId, @PathVariable("task_id") UUID taskId ){
        return taskService.getTask(taskListId,taskId).map(taskMapper::toDto);
    }

    @PutMapping(path = "/{task_id}")
    public TaskDto updateTask(@PathVariable("task_list_id") UUID taskListId, @PathVariable("task_id") UUID taskId ,@RequestBody TaskDto taskDto){
        Task updatedTask= taskService.updateTask(taskListId,taskId,taskMapper.fromDto(taskDto));
        return taskMapper.toDto(updatedTask);
    }

    @DeleteMapping(path = "/{task_id}")
    public void deleteTask(@PathVariable("task_list_id") UUID taskListId, @PathVariable("task_id") UUID taskId ){
        taskService.deleteTask(taskListId,taskId);
    }
}
