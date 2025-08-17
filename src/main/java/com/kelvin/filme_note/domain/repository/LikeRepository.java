package com.kelvin.filme_note.domain.repository;

import com.kelvin.filme_note.domain.model.Like;
import com.kelvin.filme_note.domain.model.Review;
import com.kelvin.filme_note.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface LikeRepository extends JpaRepository<Like, UUID> {
    List<Like> findByUser(User user);

    List<Like> findByReview(Review review);

    long countByReview(Review review);

    long countByReviewId(UUID reviewId);
}
