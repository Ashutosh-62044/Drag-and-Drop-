package com.Task_Board.service.Impl;

import com.Task_Board.entity.Task;
import com.Task_Board.entity.TaskList;
import com.Task_Board.exception.ResourceNotFoundException;
import com.Task_Board.payload.TaskDto;
import com.Task_Board.repository.TaskListRepository;
import com.Task_Board.repository.TaskRepository;
import com.Task_Board.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;
    private final TaskListRepository taskListRepository;

    @Autowired
    public TaskServiceImpl(TaskRepository taskRepository, TaskListRepository taskListRepository) {
        this.taskRepository = taskRepository;
        this.taskListRepository = taskListRepository;
    }

    @Override
    public TaskDto createTask(long taskListId, TaskDto taskDto) {
        // Check if the taskListId exists
        TaskList taskList = taskListRepository.findById(taskListId)
                .orElseThrow(() -> new ResourceNotFoundException("TaskList not found with id: " + taskListId));

        Task task = new Task();
        task.setName(taskDto.getName());
        task.setCompleted(taskDto.isCompleted());
        task.setTaskList(taskList);
        Task savedTask = taskRepository.save(task);

        TaskDto Dto = new TaskDto();
        Dto.setId(savedTask.getId());
        Dto.setName(savedTask.getName());
        Dto.setCompleted(savedTask.isCompleted());
        return Dto;
    }

    @Override
    public void deleteTask(long taskId) {
        taskRepository.findById(taskId).
                orElseThrow(()-> new ResourceNotFoundException("Task is not found with id: " +taskId));
        taskRepository.deleteById(taskId);
    }

    @Override
    public List<TaskDto> getDataByTaskId(long taskListId) {
        List<Task> tasks = taskRepository.findByTaskListId(taskListId);
     List<TaskDto> taskDtoList= tasks.stream().map(task -> mapToDto(task)).collect(Collectors.toList());
       return taskDtoList;
    }
     TaskDto mapToDto(Task task)
     {
         TaskDto dto = new TaskDto();
         dto.setId(task.getId());
         dto.setName(task.getName());
         dto.setCompleted(task.isCompleted());
         return dto;
    }

    // Update task
    @Override
    public TaskDto updateTask(long taskId, TaskDto taskDto) {
        Task taskToUpdate = taskRepository.findById(taskId)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found with id: " + taskId));

        taskToUpdate.setName(taskDto.getName());
        taskToUpdate.setCompleted(taskDto.isCompleted());

        Task updatedTask = taskRepository.save(taskToUpdate);

        return mapToDto(updatedTask);
    }

}
