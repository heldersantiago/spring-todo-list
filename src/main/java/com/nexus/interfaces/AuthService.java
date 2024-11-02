package com.nexus.interfaces;

import com.nexus.entities.User;

import java.util.List;

public interface AuthService {
    User register(User user);

    void login();
}
