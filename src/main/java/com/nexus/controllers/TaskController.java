package com.nexus.controllers;

import com.nexus.dtos.TaskDTO;
import com.nexus.entities.Task;
import com.nexus.interfaces.TaskService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.*;
import io.swagger.v3.oas.annotations.responses.*;
import lombok.AllArgsConstructor;
import org.hibernate.cache.spi.support.AbstractReadWriteAccess;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RequestMapping("/api/v1/tasks")
@RestController
public class TaskController {
    private final TaskService taskService;
    private final ModelMapper taskMapper = new ModelMapper();

    @Operation(summary = "Get all tasks")
    @ApiResponses(value = {@ApiResponse(responseCode = "200")})
    @GetMapping
    public ResponseEntity<List<TaskDTO>> getTasks() {
        return ResponseEntity.ok(taskService.getTasks());
    }

    @PostMapping
    public ResponseEntity<TaskDTO> createTask(@RequestBody TaskDTO taskDTO) {
        return ResponseEntity.ok(taskService.createTask(taskMapper.map(taskDTO, Task.class)));
    }

    @Operation(summary = "Get a task by its ID")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Task Found", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = AbstractReadWriteAccess.Item.class))}), @ApiResponse(responseCode = "400", description = "Bad Request"), @ApiResponse(responseCode = "404", description = "Task not found")})
    @GetMapping("/{id}")
    public ResponseEntity<TaskDTO> getTaskById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(taskService.getTaskById(id));
    }

    @Operation(summary = "Update a task")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Task updated", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = AbstractReadWriteAccess.Item.class))}), @ApiResponse(responseCode = "400", description = "Bad Request"), @ApiResponse(responseCode = "404", description = "Task not found")})
    @PatchMapping("/{id}")
    public ResponseEntity<TaskDTO> updateTask(@PathVariable("id") Long id, @RequestBody Task task) {
        return ResponseEntity.ok(taskService.updateTask(id, task));
    }

    @Operation(summary = "Delete a task")
    @ApiResponses(value = {@ApiResponse(responseCode = "204", description = "Task deleted"), @ApiResponse(responseCode = "404", description = "Task not found")})
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTask(@PathVariable("id") Long id) {
        taskService.deleteTask(id);
    }

    @Operation(summary = "Assign a task to a category")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Task assigned to a category"), @ApiResponse(responseCode = "404", description = "Task or Category not found")})
    @PostMapping("/{taskId}/assign-category/{categoryId}")
    public ResponseEntity<TaskDTO> assignCategoryToTask(@PathVariable("taskId") Long taskId, @PathVariable("categoryId") Long categoryId) {
        TaskDTO updatedTask = taskService.assignCategoryToTask(taskId, categoryId);
        return ResponseEntity.ok(updatedTask);
    }

    @Operation(summary = "Get task(s) by category")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Task(s) found"), @ApiResponse(responseCode = "404", description = "Category not found")})
    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<TaskDTO>> getTasksByCategory(@Validated @PathVariable("categoryId") Long categoryId) {
        List<TaskDTO> tasks = taskService.getTasksByCategory(categoryId);
        return ResponseEntity.ok(tasks);
    }
}
