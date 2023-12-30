package com.Task_Board.repository;

import com.Task_Board.entity.TaskList;
import com.Task_Board.payload.TaskListDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskListRepository extends JpaRepository<TaskList, Long> {

}
