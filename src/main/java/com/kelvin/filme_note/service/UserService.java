package com.kelvin.filme_note.service;

import com.kelvin.filme_note.domain.model.Role;
import com.kelvin.filme_note.domain.model.User;
import com.kelvin.filme_note.domain.model.Review;
import com.kelvin.filme_note.domain.repository.UserRepository;
import com.kelvin.filme_note.domain.repository.ReviewRepository;
import com.kelvin.filme_note.service.util.PasswordEncoder;

import java.util.List;
import java.util.UUID;

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

    // DTO para atualizar dados do usuário (opcional)
    public static class UpdateUserRequest {
        public String name;
        public String email;
        public String password;
    }

    public User register(RegisterUserRequest dto) {
        if (userRepository.findByEmail(dto.email).isPresent()) {
            throw new IllegalArgumentException("Email já cadastrado.");
        }
        User user = new User(dto.name, dto.email, PasswordEncoder.hash(dto.password), Role.USER);
        return userRepository.save(user);
    }

    public void deleteUserProfile(User user) {
        checkAuthenticated(user);

        // Marca resenhas como "autor deletado"
        List<Review> reviews = reviewRepository.findByAuthor(user);
        for (Review r : reviews) {
            r.setAuthorDeleted(true);
            reviewRepository.save(r);
        }
        userRepository.deleteById(user.getId());
    }

    public User updateUserProfile(UUID userId, UpdateUserRequest dto) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado"));
        if (dto.name != null) user.setName(dto.name);
        if (dto.email != null) user.setEmail(dto.email);
        if (dto.password != null) user.setPasswordHash(PasswordEncoder.hash(dto.password));

        return userRepository.save(user);
    }

    private void checkAuthenticated(User user) {
        if (user == null) {
            throw new SecurityException("Usuário não autenticado.");
        }
    }
}
