package com.Task_Board.controller;


import com.Task_Board.payload.TaskListDto;
import com.Task_Board.service.TaskListService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/taskList")
public class TaskListController {

    // http:localhost:8080/api/taskList
    private TaskListService taskListService;
    public TaskListController(TaskListService taskListService) {
        this.taskListService = taskListService;
    }

    @PostMapping
    public ResponseEntity<?> createTaskList(@Valid  @RequestBody TaskListDto taskListDto,
                                             BindingResult bindingResult )
    {
        if(bindingResult.hasErrors()){
            return new ResponseEntity<>(bindingResult.getFieldError().getDefaultMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        TaskListDto dto = taskListService.createTaskList(taskListDto);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

//  delete taskList by Id
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable long id){
       taskListService.deleteById(id);
        return new ResponseEntity<>("Post is deleted ",HttpStatus.OK);
    }
  @GetMapping
    public ResponseEntity<List<TaskListDto>> getAllTaskList(
            @RequestParam(name = "pageNo", defaultValue = "0", required = false) int pageNo,
            @RequestParam(name = "pageSize", defaultValue = "3",required = false) int pageSize,
            @RequestParam(name="sortBy",defaultValue = "id" ,required = false) String sortBy,
            @RequestParam(name = "sortDir", defaultValue = "asc",required = false) String sortDir
    )
    {
        List<TaskListDto> taskListDtos = taskListService.getAllTaskList( pageNo, pageSize,sortBy,sortDir);
        return new ResponseEntity<>(taskListDtos, HttpStatus.OK) ;
    }
}
