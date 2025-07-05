package com.Spring.Tasks.services.impl;

import com.Spring.Tasks.domain.entities.Task;
import com.Spring.Tasks.domain.entities.TaskList;
import com.Spring.Tasks.domain.entities.TaskPriority;
import com.Spring.Tasks.domain.entities.TaskStatus;
import com.Spring.Tasks.repositories.TaskListRepository;
import com.Spring.Tasks.repositories.TaskRepository;
import com.Spring.Tasks.services.TaskService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.InvalidIsolationLevelException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
public class TaskServiceimpl implements TaskService {

    private final  TaskRepository taskRepository;
    private final TaskListRepository taskListRepository;

    public TaskServiceimpl(TaskRepository taskRepository, TaskListRepository taskListRepository) {
        this.taskRepository = taskRepository;
        this.taskListRepository = taskListRepository;
    }

    @Override
    public List<Task> listsTasks(UUID taskListId) {
        return taskRepository.findByTaskListId(taskListId);
    }

    @Override
    public Task createTask(UUID taskListId, Task task) {
        if(task.getId()!=null){// already exists
            throw new IllegalArgumentException("Task already exists!");
        }
        if(task.getTitle()==null || task.getTitle().isBlank()){
            throw  new IllegalArgumentException("Task must have a title!");
        }
        TaskPriority taskPriority = Optional.ofNullable(task.getPriority()).orElse(TaskPriority.MEDIUM);
        TaskStatus taskStatus = TaskStatus.OPEN;
   // for which tasklist this belongs to
        TaskList taskList= taskListRepository.findById(taskListId)
                .orElseThrow(()-> new IllegalArgumentException("Invalid Task List Id provided!"));

        Task taskToSave = new Task(
                null,
                task.getTitle(),
                task.getDescription(),
                task.getDueDate(),
                taskStatus,
                taskPriority,
                taskList,
                LocalDateTime.now(),
                LocalDateTime.now()
        );
        return   taskRepository.save(taskToSave);
    }

    @Override
    public Optional<Task> getTask(UUID taskListID, UUID taskId) {
        return taskRepository.findByTaskListIdAndId(taskListID,taskId);
    }

    @Override
    public Task updateTask(UUID taskListId, UUID taskId, Task task) {
        if(task.getId()==null){
            throw new IllegalArgumentException("Task must have an ID!");
        }
        if(!Objects.equals(taskId,task.getId())){
            throw new IllegalArgumentException("Task IDs do not match!");
        }
        if(task.getPriority()==null){
            throw new IllegalArgumentException("Task must have a valid priority!");
        }
        if(null == task.getStatus()){
            throw new IllegalArgumentException("Task must have a valid status!");
        }

        Task task1 = taskRepository.findByTaskListIdAndId(taskListId,taskId).orElseThrow(()-> new IllegalArgumentException("Task not found!"));
        task1.setTitle(task.getTitle());
        task1.setDescription(task.getDescription());
        task1.setDueDate(task.getDueDate());
        task1.setUpdated(LocalDateTime.now());
        task1.setPriority(task.getPriority());
        task1.setStatus(task.getStatus());
        return taskRepository.save(task1);
    }
}