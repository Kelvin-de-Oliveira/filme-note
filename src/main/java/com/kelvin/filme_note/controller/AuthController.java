package com.kelvin.filme_note.controller;

import com.kelvin.filme_note.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthService.LoginResponse> login(@RequestBody AuthService.LoginRequest dto) {
        AuthService.LoginResponse response = authService.authenticate(dto);
        return ResponseEntity.ok(response);
    }
}
