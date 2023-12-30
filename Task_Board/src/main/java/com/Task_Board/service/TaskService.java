package com.Task_Board.service;

import com.Task_Board.payload.TaskDto;
import com.Task_Board.payload.TaskListDto;

import java.util.List;

public interface TaskService {
    TaskDto createTask(long taskListId, TaskDto tasKDto);

    void deleteTask(long taskId);


    List<TaskDto> getDataByTaskId(long taskId);

    TaskDto updateTask(long taskId, TaskDto taskDto);
}
