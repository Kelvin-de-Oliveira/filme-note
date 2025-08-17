package com.kelvin.filme_note.domain.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "tab_like")
public class Like {

    @Id
    @GeneratedValue
    private UUID id;
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = true)
    private  User user;

    @ManyToOne(optional = false)
    @JoinColumn(name = "review_id", nullable = false)
    private Review review;

    public Like(User user, Review review, LocalDateTime createdAt) {
        this.createdAt = createdAt;
        this.user = user;
        this.review = review;
    }
}
