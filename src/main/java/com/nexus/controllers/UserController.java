package com.nexus.controllers;


import com.nexus.entities.User;
import com.nexus.interfaces.UserService;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    @Autowired
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> getUsers() {
        return userService.getUsers();
    }

    @GetMapping("/:id")
    public Optional<User> getUser(@PathParam("id") Long id) {
        return userService.getUserById(id);
    }

    @DeleteMapping("/:id")
    public void deleteUser(@PathParam("id") Long id) {
        userService.deleteUser(id);
    }
}