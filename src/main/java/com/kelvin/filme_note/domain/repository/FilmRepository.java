package com.kelvin.filme_note.domain.repository;

import com.kelvin.filme_note.domain.model.Film;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import java.util.UUID;

public interface FilmRepository extends JpaRepository<Film, UUID> {
    Optional<Film> findByTitle(String title);
}
