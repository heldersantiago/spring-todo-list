package com.nexus.dtos;

import com.nexus.enums.TaskPriority;
import com.nexus.enums.TaskStatus;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class TaskDTO {
    private Long id;
    private Long categoryId;
    private String title;
    private String description;
    private TaskPriority priority;
    private TaskStatus status;

    // Constructor, Getters, Setters
    public TaskDTO(Long id, String title, String description, TaskPriority taskPriority, TaskStatus taskStatus, Long categoryId) {
        this.id = id;
        this.title = title;
        this.categoryId = categoryId;
        this.status = taskStatus;
        this.priority = taskPriority;
        this.description = description;
    }
}
