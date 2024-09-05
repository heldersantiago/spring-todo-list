package com.nexus.services;

import com.nexus.entities.Task;
import com.nexus.exceptions.TaskNotFoundException;
import com.nexus.interfaces.TaskService;
import com.nexus.repositories.TaskRepository;
import jakarta.persistence.EntityNotFoundException;
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
    public Optional<Task> getTaskById(Long id) throws TaskNotFoundException {
        return Optional.ofNullable(taskRepository.findById(id).orElseThrow(() -> new TaskNotFoundException("Task Not Found")));
    }

    @Override
    public Task updateTask(Long id, Task taskDTO) {
        return taskRepository.findById(id).map(task -> {
            if (taskDTO.getTitle() != null) {
                task.setTitle(taskDTO.getTitle());
            }

            if (taskDTO.getDescription() != null) {
                task.setDescription(taskDTO.getDescription());
            }
            if (taskDTO.getPriority() != null) {
                task.setPriority(taskDTO.getPriority());
            }

            if (taskDTO.getStatus() != null) {
                task.setStatus(taskDTO.getStatus());
            }

            if (taskDTO.getDueDate() != null) {
                task.setDueDate(taskDTO.getDueDate());
            }
            return taskRepository.save(task);
        }).orElseThrow(() -> new EntityNotFoundException(""));
    }

    @Override
    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }

    public List<Task> getTasks() {
        return taskRepository.findAll();
    }
}
