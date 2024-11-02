package com.nexus.services;


import com.nexus.entities.User;
import com.nexus.exceptions.ResourceNotFoundException;
import com.nexus.interfaces.UserService;
import com.nexus.repositories.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public Iterable<User> getAll(Sort sort) {
        return null;
    }

    @Override
    public Page<User> getAll(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Override
    public User getById(Long id) {
        return userRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public User create(User user) {
        return null;
    }

    @Override
    public User update(Long id, User userDTO) {
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
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

}
