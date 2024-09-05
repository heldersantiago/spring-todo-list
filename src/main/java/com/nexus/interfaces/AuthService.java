package com.nexus.interfaces;

import com.nexus.entities.User;

import java.util.List;

public interface AuthService {
    public User register(User user);
    public void login();
}
