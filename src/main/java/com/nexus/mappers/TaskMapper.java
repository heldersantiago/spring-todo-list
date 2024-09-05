package com.nexus.mappers;

import com.nexus.dtos.TaskDTO;
import com.nexus.entities.Category;
import com.nexus.entities.Task;

public class TaskMapper {

    public static TaskDTO toDTO(Task task) {
        return new TaskDTO(
                task.getId(),
                task.getTitle(),
                task.getDescription(),
                task.getPriority(),
                task.getStatus(),
                task.getCategory() != null ? task.getCategory().getId() : null
        );
    }

    public static Task toEntity(TaskDTO taskDTO, Category category) {
        Task task = new Task();
        task.setId(taskDTO.getId());
        task.setTitle(taskDTO.getTitle());
        task.setDescription(taskDTO.getDescription());
        task.setPriority(taskDTO.getPriority());
        task.setStatus(taskDTO.getStatus());
        task.setCategory(category);
        return task;
    }
}
