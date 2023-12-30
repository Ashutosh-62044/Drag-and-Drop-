package com.Task_Board.service;

import com.Task_Board.entity.TaskList;
import com.Task_Board.payload.TaskListDto;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TaskListService {

    TaskListDto createTaskList(TaskListDto taskListDto);

    void deleteById(long id);


    List<TaskListDto> getAllTaskList(int pageNo, int pageSize, String sortBy, String sortDir);
}
