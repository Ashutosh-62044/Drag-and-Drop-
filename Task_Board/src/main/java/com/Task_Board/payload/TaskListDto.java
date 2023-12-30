package com.Task_Board.payload;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskListDto {

    private Long id;
    @NotEmpty
    @Size(min=2, message="name should be at least 2 characters")
    private String name;

    public boolean completed;

}
