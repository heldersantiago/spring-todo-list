package com.nexus.services;


import com.nexus.entities.User;
import com.nexus.interfaces.UserService;
import com.nexus.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private final UserRepository userRepository;

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Optional<User> getUserById(Long id) {
        return Optional.ofNullable(userRepository.findById(id).orElse(null));
    }

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }
}
