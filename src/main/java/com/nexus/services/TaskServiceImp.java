package com.nexus.services;

import com.nexus.dtos.TaskDTO;
import com.nexus.entities.Category;
import com.nexus.entities.Task;
import com.nexus.exceptions.ResourceNotFoundException;
import com.nexus.interfaces.TaskService;
import com.nexus.repositories.CategoryRepository;
import com.nexus.repositories.TaskRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class TaskServiceImp implements TaskService {
    private final TaskRepository taskRepository;
    private final CategoryRepository categoryRepository;
    private final ModelMapper taskMapper = new ModelMapper();

    @Override
    public Iterable<Task> getAll(Sort sort) {
        return null;
    }

    @Override
    public Page<Task> getAll(Pageable pageable) {
        return taskRepository.findAll(pageable);
    }

    @Override
    public Task create(Task task) {
        return taskRepository.save(task);
    }

    @Override
    public Task getById(Long id) {
        return taskRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Task Not Found"));
    }

    @Override
    public Task update(Long id, Task taskDTO) {
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

            return task;
        }).orElseThrow(() -> new EntityNotFoundException("Task not found"));
    }

    @Override
    public void delete(Long id) {
        Task task = taskRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
        taskRepository.delete(task);
    }

    @Override
    public Task assignCategoryToTask(Long taskId, Long categoryId) {
        Task task = taskRepository.findById(taskId).orElseThrow(() -> new ResourceNotFoundException("Task not found"));
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new EntityNotFoundException("Category not Found"));
        task.setCategory(category);
        return taskRepository.save(task);
    }

    public List<Task> getTasksByCategory(Long categoryId) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category not Found"));
        return taskRepository.findByCategoryId(categoryId).stream().toList();
    }
}
