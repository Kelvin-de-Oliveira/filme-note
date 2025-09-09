package com.kelvin.filme_note.service;

import com.kelvin.filme_note.domain.model.User;
import com.kelvin.filme_note.domain.repository.UserRepository;
import com.kelvin.filme_note.dto.auth.LoginRequest;
import com.kelvin.filme_note.dto.auth.LoginResponse;
import com.kelvin.filme_note.service.util.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
@Service
public class AuthService {

    private final UserRepository userRepository;

    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public LoginResponse authenticate(LoginRequest dto) {
        User user;

        // Decide se é email ou nome
        if (dto.emailOrName.contains("@")) {
            user = userRepository.findByEmail(dto.emailOrName)
                    .orElseThrow(() -> new NoSuchElementException("Usuário não encontrado"));
        } else {
            user = userRepository.findByName(dto.emailOrName)
                    .orElseThrow(() -> new NoSuchElementException("Usuário não encontrado"));
        }

        if (!PasswordEncoder.matches(dto.password, user.getPasswordHash())) {
            throw new IllegalArgumentException("Senha inválida");
        }

        return new LoginResponse(user);
    }
}
