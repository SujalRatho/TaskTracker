package com.Spring.Tasks.repositories;

import com.Spring.Tasks.domain.entities.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface TaskRepository extends JpaRepository<Task, UUID> {
    // custom methods to handle tasks
    List<Task> findByTaskListId(UUID taskListId);
    Optional<Task> findByTaskListIdAndId(UUID taskListId,UUID id);
    void deleteByTaskListIdAndId(UUID taskListId,UUID id);
}
