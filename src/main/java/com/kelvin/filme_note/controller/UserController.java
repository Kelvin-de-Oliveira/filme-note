package com.kelvin.filme_note.controller;

import com.kelvin.filme_note.domain.model.User;
import com.kelvin.filme_note.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<UserService.UserResponse> register(@RequestBody UserService.RegisterUserRequest dto) {
        UserService.UserResponse response = userService.register(dto);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<UserService.UserResponse> update(@PathVariable UUID userId,
                                                           @RequestBody UserService.UpdateUserRequest dto) {
        UserService.UserResponse updated = userService.updateUserProfile(userId, dto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<UserService.UserResponse> delete(@PathVariable UUID userId) {
        User user = userService.findById(userId);
        UserService.UserResponse response = userService.deleteUserProfile(user);
        return ResponseEntity.ok(response);
    }
}
