package com.kelvin.filme_note.domain.repository;

import com.kelvin.filme_note.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findByEmail(String email);
    Optional<User> findByName(String name);

    boolean existsByEmail(String email);
    boolean existsByName(String name);
}
