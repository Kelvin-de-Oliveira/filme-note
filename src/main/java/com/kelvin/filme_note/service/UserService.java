package com.kelvin.filme_note.service;

import com.kelvin.filme_note.domain.model.Role;
import com.kelvin.filme_note.domain.model.User;
import com.kelvin.filme_note.domain.model.Review;
import com.kelvin.filme_note.domain.repository.UserRepository;
import com.kelvin.filme_note.domain.repository.ReviewRepository;
import com.kelvin.filme_note.service.util.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final ReviewRepository reviewRepository;

    public UserService(UserRepository userRepository, ReviewRepository reviewRepository) {
        this.userRepository = userRepository;
        this.reviewRepository = reviewRepository;
    }


    // DTO para cadastro de usuário
    public static class RegisterUserRequest {
        public String name;
        public String email;
        public String password;
    }

    // DTO para atualizar dados do usuário
    public static class UpdateUserRequest {
        public String name;
        public String email;
        public String password;
    }

    // DTO de resposta para usuário
    public static class UserResponse {
        public UUID id;
        public String name;
        public String email;

        public UserResponse(User user) {
            this.id = user.getId();
            this.name = user.getName();
            this.email = user.getEmail();
        }
    }

    public UserResponse register(RegisterUserRequest dto) {
        if (userRepository.findByEmail(dto.email).isPresent()) {
            throw new IllegalArgumentException("Email já cadastrado.");
        }
        User user = new User(dto.name, dto.email, PasswordEncoder.hash(dto.password), Role.USER);
        userRepository.save(user);
        return new UserResponse(user);
    }

    public UserResponse deleteUserProfile(User user) {
        checkAuthenticated(user);

        // Marca resenhas como "autor deletado"
        List<Review> reviews = reviewRepository.findByAuthor(user);
        for (Review r : reviews) {
            r.setAuthorDeleted(true);
            reviewRepository.save(r);
        }
        userRepository.deleteById(user.getId());
        return new UserResponse(user);
    }

    public UserResponse updateUserProfile(UUID userId, UpdateUserRequest dto) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NoSuchElementException("Usuário não encontrado"));
        if (dto.name != null) user.setName(dto.name);
        if (dto.email != null) user.setEmail(dto.email);
        if (dto.password != null) user.setPasswordHash(PasswordEncoder.hash(dto.password));

        userRepository.save(user);
        return new UserResponse(user);
    }

    public User findById(UUID userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new NoSuchElementException("Usuário não encontrado"));
    }

    private void checkAuthenticated(User user) {
        if (user == null) {
            throw new SecurityException("Usuário não autenticado.");
        }
    }
}
