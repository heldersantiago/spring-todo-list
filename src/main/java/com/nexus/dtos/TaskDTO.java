package com.nexus.dtos;

import com.nexus.enums.TaskPriority;
import com.nexus.enums.TaskStatus;
import lombok.*;

import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskDTO {
    private Long id;
    private Long categoryId;
    private String title;
    private String description;
    private TaskPriority priority;
    private TaskStatus status;
    private Date dueDate;
}
