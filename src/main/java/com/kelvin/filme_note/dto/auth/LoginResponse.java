package com.kelvin.filme_note.dto.auth;

import com.kelvin.filme_note.domain.model.User;

public class LoginResponse {
    public String userId;
    public String name;
    public String role;

    public LoginResponse(User user) {
        this.userId = user.getId().toString();
        this.name = user.getName();
        this.role = user.getRole().name();
    }
}