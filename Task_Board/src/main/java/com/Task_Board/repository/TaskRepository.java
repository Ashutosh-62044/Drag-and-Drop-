package com.Task_Board.repository;

import com.Task_Board.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
     // List<Task> findByTaskId(long taskId);
      List<Task> findByTaskListId(long taskListId);
}
