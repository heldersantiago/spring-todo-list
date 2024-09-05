package com.nexus.controllers;

import com.nexus.dtos.TaskDTO;
import com.nexus.entities.Task;
import com.nexus.exceptions.TaskNotFoundException;
import com.nexus.interfaces.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tasks")
public class TaskController {
    @Autowired
    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping
    public TaskDTO createTask(@RequestBody Task task) {
        return taskService.createTask(task);
    }

    @GetMapping
    public List<TaskDTO> getTasks() {
        return taskService.getTasks();
    }

    @GetMapping("/{id}")
    public TaskDTO getTaskById(@PathVariable("id") Long id) throws TaskNotFoundException {
        return taskService.getTaskById(id);
    }


    @PatchMapping("/{id}")
    public TaskDTO updateTask(@PathVariable("id") Long id, @RequestBody Task task) {
        return taskService.updateTask(id, task);
    }

    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable("id") Long id) {
        taskService.deleteTask(id);
    }

    @PostMapping("/{taskId}/assign-category/{categoryId}")
    public ResponseEntity<TaskDTO> assignCategoryToTask(@PathVariable("taskId") Long taskId, @PathVariable("categoryId") Long categoryId) throws TaskNotFoundException {
        TaskDTO updatedTask = taskService.assignCategoryToTask(taskId, categoryId);
        return ResponseEntity.ok(updatedTask);
    }

    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<TaskDTO>> getTasksByCategory(@PathVariable("categoryId") Long categoryId) {
        List<TaskDTO> tasks = taskService.getTasksByCategory(categoryId);
        if (tasks.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(tasks);
    }
}
