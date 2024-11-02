package com.nexus.interfaces;

import com.nexus.dtos.TaskDTO;
import com.nexus.entities.Task;
import com.nexus.exceptions.ResourceNotFoundException;

import java.util.List;

public interface TaskService {
    public TaskDTO createTask(Task task);

    public List<TaskDTO> getTasks();

    public TaskDTO getTaskById(Long id);

    public TaskDTO updateTask(Long id, Task task);

    public void deleteTask(Long id);

    public TaskDTO assignCategoryToTask(Long taskId, Long categoryId);

    public List<TaskDTO> getTasksByCategory(Long categoryId);
}
