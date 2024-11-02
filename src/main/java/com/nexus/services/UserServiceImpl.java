package com.nexus.services;


import com.nexus.entities.User;
import com.nexus.exceptions.ResourceNotFoundException;
import com.nexus.interfaces.UserService;
import com.nexus.repositories.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

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
    public Optional<User> getUserById(Long id) throws ResourceNotFoundException {
        return Optional.ofNullable(userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User Not found")));
    }

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }
}
