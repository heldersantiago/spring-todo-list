package com.nexus.controllers;

import com.nexus.entities.Task;
import com.nexus.interfaces.TaskService;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/tasks")
public class TaskController {
    @Autowired
    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping
    public Task createTask(@RequestBody Task task) {
        return taskService.createTask(task);
    }

    @GetMapping
    public List<Task> getTasks() {
        return taskService.getTasks();
    }

    @GetMapping("/:id")
    public Optional<Task> getTaskById(@PathParam("id") Long id) {
        return taskService.getTaskById(id);
    }

    @DeleteMapping("/:id")
    public void deleteTask(@PathParam("id") Long id) {
        taskService.deleteTask(id);
    }
}
