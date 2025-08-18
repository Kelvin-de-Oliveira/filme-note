package com.kelvin.filme_note.controller;

import com.kelvin.filme_note.domain.model.Review;
import com.kelvin.filme_note.domain.model.User;
import com.kelvin.filme_note.service.ReviewService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/reviews")
public class ReviewController {

    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @PostMapping
    public ResponseEntity<Review> create(@RequestAttribute("user") User user,
                                         @RequestBody ReviewService.CreateReviewRequest dto) {
        return ResponseEntity.ok(reviewService.createReview(user, dto));
    }

    @PutMapping("/{reviewId}")
    public ResponseEntity<Review> update(@RequestAttribute("user") User user,
                                         @PathVariable UUID reviewId,
                                         @RequestBody ReviewService.UpdateReviewRequest dto) {
        return ResponseEntity.ok(reviewService.updateReview(user, reviewId, dto));
    }

    @DeleteMapping("/{reviewId}")
    public ResponseEntity<Void> delete(@RequestAttribute("user") User user,
                                       @PathVariable UUID reviewId) {
        reviewService.deleteReview(user, reviewId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/me")
    public ResponseEntity<List<Review>> getMyReviews(@RequestAttribute("user") User user) {
        return ResponseEntity.ok(reviewService.getUserReviews(user));
    }

    @GetMapping("/me/{reviewId}")
    public ResponseEntity<Review> getMyReviewById(@RequestAttribute("user") User user,
                                                  @PathVariable UUID reviewId) {
        return reviewService.getUserReviewById(user, reviewId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/film/{filmId}")
    public ResponseEntity<List<Review>> getByFilm(@PathVariable UUID filmId) {
        return ResponseEntity.ok(reviewService.getReviewsByFilm(filmId));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Review>> getByUser(@PathVariable UUID userId) {
        return ResponseEntity.ok(reviewService.getReviewsByAnotherUser(userId));
    }

    @PostMapping("/{reviewId}/like")
    public ResponseEntity<Void> like(@RequestAttribute("user") User user,
                                     @PathVariable UUID reviewId) {
        reviewService.likeReview(user, reviewId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{reviewId}/likes")
    public ResponseEntity<Long> likesCount(@PathVariable UUID reviewId) {
        return ResponseEntity.ok(reviewService.getLikesCount(reviewId));
    }
}
