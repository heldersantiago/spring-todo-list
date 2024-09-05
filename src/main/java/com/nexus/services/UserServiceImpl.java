package com.nexus.services;


import com.nexus.entities.User;
import com.nexus.exceptions.UserNotFoundException;
import com.nexus.interfaces.UserService;
import com.nexus.repositories.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public User updateUser(Long id, User userDTO) {
        return userRepository.findById(id).map(user -> {
            if (userDTO.getName() != null) {
                user.setName(userDTO.getName());
            }
            if (userDTO.getEmail() != null) {
                user.setEmail(userDTO.getEmail());
            }
            return userRepository.save(user);
        }).orElseThrow(() -> new EntityNotFoundException("User not found with ID: " + id));
    }

    @Override
    public Optional<User> getUserById(Long id) throws UserNotFoundException {
        return Optional.ofNullable(userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User Not found")));
    }

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }
}
