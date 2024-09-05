package com.nexus.interfaces;

import com.nexus.entities.Task;
import com.nexus.exceptions.TaskNotFoundException;

import java.util.List;
import java.util.Optional;

public interface TaskService {
    public Task createTask(Task task);

    public List<Task> getTasks();

    public Optional<Task> getTaskById(Long id) throws TaskNotFoundException;

    public Task updateTask(Long id, Task task);

    public void deleteTask(Long id);
}
