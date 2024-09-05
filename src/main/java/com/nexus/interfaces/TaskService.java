package com.nexus.interfaces;

import com.nexus.dtos.TaskDTO;
import com.nexus.entities.Task;
import com.nexus.exceptions.TaskNotFoundException;

import java.util.List;

public interface TaskService {
    public TaskDTO createTask(Task task);

    public List<TaskDTO> getTasks();

    public TaskDTO getTaskById(Long id) throws TaskNotFoundException;

    public TaskDTO updateTask(Long id, Task task);

    public void deleteTask(Long id);

    public TaskDTO assignCategoryToTask(Long taskId, Long categoryId) throws TaskNotFoundException;

    public List<TaskDTO> getTasksByCategory(Long categoryId);
}
