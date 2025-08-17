package com.kelvin.filme_note.service;

import com.kelvin.filme_note.domain.model.Film;
import com.kelvin.filme_note.domain.model.Like;
import com.kelvin.filme_note.domain.model.Review;
import com.kelvin.filme_note.domain.model.User;
import com.kelvin.filme_note.domain.repository.FilmRepository;
import com.kelvin.filme_note.domain.repository.LikeRepository;
import com.kelvin.filme_note.domain.repository.ReviewRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final LikeRepository likeRepository;
    private final FilmRepository filmRepository;

    public ReviewService(ReviewRepository reviewRepository, LikeRepository likeRepository, FilmRepository filmRepository) {
        this.reviewRepository = reviewRepository;
        this.likeRepository = likeRepository;
        this.filmRepository = filmRepository;
    }

    // DTO para criar resenha
    public static class CreateReviewRequest {
        public UUID filmId;
        public String description;
        public int score;
    }

    // DTO para atualizar resenha
    public static class UpdateReviewRequest {
        public String description;
        public Integer score;
    }

    public Review createReview(User user, CreateReviewRequest dto) {
        checkAuthenticated(user);
        Film film = filmRepository.findById(dto.filmId)
                .orElseThrow(() -> new IllegalArgumentException("Filme não encontrado"));
        Review review = new Review(dto.description, dto.score, user, film, LocalDateTime.now());
        return reviewRepository.save(review);
    }

    public Review updateReview(User user, UUID reviewId, UpdateReviewRequest dto) {
        checkAuthenticated(user);
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new IllegalArgumentException("Resenha não encontrada"));

        if (!review.getAuthor().equals(user)) {
            throw new SecurityException("Você só pode alterar suas próprias resenhas");
        }
        if (dto.description != null) review.setDescription(dto.description);
        if (dto.score != null) {
            if (dto.score < 0 || dto.score > 10) {
                throw new IllegalArgumentException("A nota deve estar entre 0 e 10.");
            }
            review.setScore(dto.score);
        }
        return reviewRepository.save(review);
    }


    public void deleteReview(User user, UUID reviewId) {
        checkAuthenticated(user);
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new IllegalArgumentException("Resenha não encontrada"));
        if (!review.getAuthor().equals(user)) {
            throw new SecurityException("Você só pode deletar suas próprias resenhas");
        }
        reviewRepository.delete(review);
    }

    public Optional<Review> getUserReviewById(User user, UUID reviewId) {
        checkAuthenticated(user);
        return reviewRepository.findByIdAndAuthor(reviewId, user);
    }

    public List<Review> getUserReviews(User user) {
        checkAuthenticated(user);
        return reviewRepository.findByAuthor(user);
    }

    public List<Review> getReviewsByFilm(UUID filmId) {
        return reviewRepository.findByFilmId(filmId);
    }

    public List<Review> getReviewsByAnotherUser(UUID userId) {
        return reviewRepository.findByAuthorId(userId);
    }

    public void likeReview(User user, UUID reviewId) {
        checkAuthenticated(user);
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new IllegalArgumentException("Resenha não encontrada"));
        Like like = new Like(user, review, LocalDateTime.now());
        review.getLikes().add(like);
        likeRepository.save(like);
    }

    public long getLikesCount(UUID reviewId) {
        return likeRepository.countByReviewId(reviewId);
    }

    private void checkAuthenticated(User user) {
        if (user == null) {
            throw new SecurityException("Usuário não autenticado.");
        }
    }
}
