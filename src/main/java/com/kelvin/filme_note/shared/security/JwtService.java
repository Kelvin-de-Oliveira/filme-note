package com.kelvin.filme_note.shared.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import jakarta.annotation.PostConstruct;
import io.jsonwebtoken.security.Keys;
import java.security.Key;

@Service
public class JwtService {
    @Value("${jwt.secret}")
    private String secret;

    private  Key secretKey;

    @PostConstruct
    public void init() {
        secretKey = Keys.hmacShaKeyFor(secret.getBytes());
    }


}
