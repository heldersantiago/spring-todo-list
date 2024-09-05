package com.nexus.interfaces;

import com.nexus.entities.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    public List<User> getUsers();

    public Optional<User> getUserById(Long id);

    public void deleteUser(Long id);
}
