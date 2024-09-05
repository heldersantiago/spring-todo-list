package com.nexus.services;

import com.nexus.entities.Task;
import com.nexus.interfaces.TaskService;
import com.nexus.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskServiceImp implements TaskService {
    @Autowired
    private final TaskRepository taskRepository;

    public TaskServiceImp(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public Task createTask(Task task) {
        return taskRepository.save(task);
    }

    @Override
    public Optional<Task> getTaskById(Long id) {
        return taskRepository.findById(id);
    }

    @Override
    public Task updateTask(Long id, Task task) {
        Optional<Task> task_ = taskRepository.findById(id);
        return task_.orElse(null);
    }

    @Override
    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }

    public List<Task> getTasks() {
        return taskRepository.findAll();
    }
}
