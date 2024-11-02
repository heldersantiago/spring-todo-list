package com.nexus.interfaces;

import com.nexus.dtos.TaskDTO;
import com.nexus.entities.Task;
import com.nexus.exceptions.ResourceNotFoundException;

import java.util.List;

public interface TaskService extends BaseService<Task> {
    Task assignCategoryToTask(Long taskId, Long categoryId);

    List<Task> getTasksByCategory(Long categoryId);
}
