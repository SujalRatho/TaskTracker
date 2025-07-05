package com.Spring.Tasks.controllers;

import com.Spring.Tasks.domain.dto.TaskListDto;
import com.Spring.Tasks.domain.entities.TaskList;
import com.Spring.Tasks.mappers.TaskListMapper;
import com.Spring.Tasks.services.TaskListService;
import org.springframework.web.bind.annotation.*;

import java.nio.file.Path;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(path = "api/task-lists")
public class TaskListController {

    public TaskListController(TaskListService taskListService, TaskListMapper taskListMapper) {
        this.taskListService = taskListService;
        this.taskListMapper = taskListMapper;
    }

    private final TaskListService taskListService;
    private final TaskListMapper taskListMapper;

    @GetMapping
    public List<TaskListDto> listTaskLists(){
         return taskListService.listTaskLists()
                 .stream()
                 .map(taskListMapper::toDto)
                 .toList();
    }

    @PostMapping
    public TaskListDto createTaskList(@RequestBody TaskListDto taskListDto){
        TaskList createdTaskList=  taskListService.createTaskList(
                taskListMapper.fromDto(taskListDto));
        return taskListMapper.toDto(createdTaskList);
    }

    @GetMapping(path = "/{task_list_id}")
    public Optional<TaskListDto> getTaskList(@PathVariable("task_list_id") UUID tasklistId){
        return taskListService.getTaskList(tasklistId).map(taskListMapper::toDto);
    }

    @PutMapping(path = "/{task_list_id}")
    public TaskListDto updateTaskList(@PathVariable("task_list_id") UUID id,@RequestBody TaskListDto taskListDto){
        TaskList updateTaskList =  taskListService.updateTaskList(id,taskListMapper.fromDto(taskListDto));
        return taskListMapper.toDto(updateTaskList);
    }

    @DeleteMapping(path = "/{task_list_id}")
    public void deletetaskList(@PathVariable("task_list_id") UUID tasklistId){
        taskListService.deleteTaskList(tasklistId);

    }
}
