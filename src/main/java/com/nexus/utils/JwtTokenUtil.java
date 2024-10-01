package com.nexus.utils;

import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;

@Component
public class JwtTokenUtil {
    private static final String SECRET_KEY = "root.dev@1830shsu*jhsu399@@e49191>></!";
    private static final int EXPIRATION_MS = 1000 * 60 * 60 * 10; // 10 hours

    private Key getSigningkey() {
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
    }
}
