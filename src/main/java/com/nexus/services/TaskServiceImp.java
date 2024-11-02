package com.nexus.services;

import com.nexus.dtos.TaskDTO;
import com.nexus.entities.Category;
import com.nexus.entities.Task;
import com.nexus.exceptions.TaskNotFoundException;
import com.nexus.interfaces.TaskService;
import com.nexus.mappers.TaskMapper;
import com.nexus.repositories.CategoryRepository;
import com.nexus.repositories.TaskRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class TaskServiceImp implements TaskService {
    private final TaskRepository taskRepository;
    private final CategoryRepository categoryRepository;
    private final ModelMapper taskMapper = new ModelMapper();

    @Override
    public TaskDTO createTask(Task task) {
        Task savedTask = taskRepository.save(task);
        return taskMapper.map(savedTask, TaskDTO.class);
    }

    @Override
    public TaskDTO getTaskById(Long id) throws TaskNotFoundException {
        Task task = taskRepository.findById(id).orElseThrow(() -> new TaskNotFoundException("Task Not Found"));
        return taskMapper.map(task, TaskDTO.class);
    }

    @Override
    public TaskDTO updateTask(Long id, Task taskDTO) {
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
            Task updatedTask = taskRepository.save(task);

            return taskMapper.map(updatedTask, TaskDTO.class);
        }).orElseThrow(() -> new EntityNotFoundException("Task not found"));
    }

    @Override
    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }

    @Override
    public TaskDTO assignCategoryToTask(Long taskId, Long categoryId) throws TaskNotFoundException {
        Task task = taskRepository.findById(taskId).orElseThrow(() -> new TaskNotFoundException("Task not found"));

        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new EntityNotFoundException("Category not Found"));

        task.setCategory(category);
        Task updatedTask = taskRepository.save(task);

        return taskMapper.map(updatedTask, TaskDTO.class);
    }

    public List<TaskDTO> getTasks() {
        return taskRepository.findAll().stream().map(task -> taskMapper.map(task, TaskDTO.class)).collect(Collectors.toList());
    }

    public List<TaskDTO> getTasksByCategory(Long categoryId) {
        return taskRepository.findByCategoryId(categoryId).stream().map(task -> taskMapper.map(task, TaskDTO.class)).collect(Collectors.toList());
    }
}
