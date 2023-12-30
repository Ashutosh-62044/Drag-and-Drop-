package com.Task_Board.controller;

import com.Task_Board.payload.TaskDto;
import com.Task_Board.service.Impl.TaskServiceImpl;
import com.Task_Board.service.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

        private TaskService taskService;

        public TaskController(TaskService taskService) {
            this.taskService = taskService;
        }

        @PostMapping
        public ResponseEntity<TaskDto> createTask(@RequestParam("taskListId") long taskListId,
                                                  @RequestBody TaskDto taskDto)
        {
            TaskDto dto = taskService.createTask(taskListId, taskDto);
            return new ResponseEntity<>(dto, HttpStatus.CREATED);
        }

  @DeleteMapping("/{taskId}")
       public ResponseEntity<String> deleteTask(@PathVariable("taskId") long taskId)
       {
           taskService.deleteTask(taskId);
          return new ResponseEntity<>("delete is successfully", HttpStatus.OK);
       }
    @GetMapping("/{taskId}")
    public ResponseEntity<List<TaskDto>> getDataByTaskId(@PathVariable long taskId) {
        List<TaskDto> taskDtoList = taskService.getDataByTaskId(taskId);

        if (taskDtoList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
          return new ResponseEntity<>(taskDtoList, HttpStatus.OK);
        }

    @PutMapping("/{taskId}")
    public ResponseEntity<TaskDto> updateTask(@PathVariable long taskId,
                                              @RequestBody TaskDto taskDto) {
        TaskDto updatedTaskDto = taskService.updateTask(taskId, taskDto);
        return new ResponseEntity<>(updatedTaskDto, HttpStatus.OK);
    }

}

