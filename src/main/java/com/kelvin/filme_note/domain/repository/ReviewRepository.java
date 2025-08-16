package com.kelvin.filme_note.domain.repository;

import com.kelvin.filme_note.domain.model.Film;
import com.kelvin.filme_note.domain.model.Review;
import com.kelvin.filme_note.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.UUID;

public interface ReviewRepository extends JpaRepository<Review, UUID> {
    List<Review> findByAuthor(User author);

    List<Review> findByFilm(Film film);
}
