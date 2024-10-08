package com.nexus.controllers;

import com.nexus.dtos.TaskDTO;
import com.nexus.entities.Task;
import com.nexus.exceptions.TaskNotFoundException;
import com.nexus.interfaces.TaskService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.*;
import io.swagger.v3.oas.annotations.responses.*;
import org.hibernate.cache.spi.support.AbstractReadWriteAccess;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Operation(summary = "Create a task")
    @ApiResponses(value = {@ApiResponse(responseCode = "201", description = "Task created", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = AbstractReadWriteAccess.Item.class))}), @ApiResponse(responseCode = "400", description = "Bad request"),})
    @PostMapping
    public TaskDTO createTask(@RequestBody Task task) {
        return taskService.createTask(task);
    }

    @Operation(summary = "Get all tasks")
    @ApiResponses(value = {@ApiResponse(responseCode = "200")})
    @GetMapping
    public List<TaskDTO> getTasks() {
        return taskService.getTasks();
    }

    @Operation(summary = "Get a task by its ID")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Task Found", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = AbstractReadWriteAccess.Item.class))}), @ApiResponse(responseCode = "400", description = "Bad Request"), @ApiResponse(responseCode = "404", description = "Task not found")})
    @GetMapping("/{id}")
    public TaskDTO getTaskById(@PathVariable("id") Long id) throws TaskNotFoundException {
        return taskService.getTaskById(id);
    }


    @Operation(summary = "Update a task")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Task updated", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = AbstractReadWriteAccess.Item.class))}), @ApiResponse(responseCode = "400", description = "Bad Request"), @ApiResponse(responseCode = "404", description = "Task not found")})
    @PatchMapping("/{id}")
    public TaskDTO updateTask(@PathVariable("id") Long id, @RequestBody Task task) {
        return taskService.updateTask(id, task);
    }

    @Operation(summary = "Delete a task")
    @ApiResponses(value = {@ApiResponse(responseCode = "204", description = "Task deleted"), @ApiResponse(responseCode = "404", description = "Task not found")})
    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable("id") Long id) {
        taskService.deleteTask(id);
    }

    @Operation(summary = "Assign a task to a category")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Task assigned to a category"), @ApiResponse(responseCode = "404", description = "Task or Category not found")})
    @PostMapping("/{taskId}/assign-category/{categoryId}")
    public ResponseEntity<TaskDTO> assignCategoryToTask(@PathVariable("taskId") Long taskId, @PathVariable("categoryId") Long categoryId) throws TaskNotFoundException {
        TaskDTO updatedTask = taskService.assignCategoryToTask(taskId, categoryId);
        return ResponseEntity.ok(updatedTask);
    }

    @Operation(summary = "Get task(s) by category")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Task(s) found"), @ApiResponse(responseCode = "404", description = "Category not found")})
    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<TaskDTO>> getTasksByCategory(@PathVariable("categoryId") Long categoryId) {
        List<TaskDTO> tasks = taskService.getTasksByCategory(categoryId);
        if (tasks.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(tasks);
    }
}
