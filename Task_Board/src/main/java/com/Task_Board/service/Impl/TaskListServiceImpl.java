package com.Task_Board.service.Impl;

import com.Task_Board.entity.TaskList;
import com.Task_Board.exception.ResourceNotFoundException;
import com.Task_Board.payload.TaskListDto;
import com.Task_Board.repository.TaskListRepository;
import com.Task_Board.service.TaskListService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskListServiceImpl implements TaskListService {

    private TaskListRepository taskListRepository;

    public TaskListServiceImpl(TaskListRepository taskListRepository) {
        this.taskListRepository = taskListRepository;
    }


    @Override
    public TaskListDto createTaskList(TaskListDto taskListDto) {
        // Create a new Post entity and map fields from the Postdto
        TaskList taskList = new TaskList();
        taskList.setName(taskListDto.getName());
        TaskList savedtaskList = taskListRepository.save(taskList);

        TaskListDto Dto = new TaskListDto();
        Dto.setId(savedtaskList.getId());
        Dto.setName(savedtaskList.getName());
        return Dto;
    }

    @Override
    public void deleteById(long id)
    {

        TaskList taskList = taskListRepository.findById(id).orElseThrow(
                ()->new ResourceNotFoundException("Post not found with id:" +id)
        );
        taskListRepository.deleteById(id);
    }

    @Override
    public List<TaskListDto> getAllTaskList(int pageNo, int pageSize, String sortBy, String sortDir) {

        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())? Sort.by(sortBy)
                .ascending():Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
        Page<TaskList> pageTaskList = taskListRepository.findAll(pageable);
        List<TaskList> taskLists = pageTaskList.getContent();
        List<TaskListDto> dtos = taskLists.stream().map(post -> mapToDto(post)).collect(Collectors.toList());
        return dtos;
    }

    TaskListDto mapToDto(TaskList taskList)
    {
      TaskListDto dto = new TaskListDto();
      dto.setId(taskList.getId());
      dto.setName(taskList.getName());
      return dto;
    }

}
