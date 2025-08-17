package com.kelvin.filme_note.domain.model;

import jakarta.persistence.*;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "tab_review")
public class Review {
    @Id
    @GeneratedValue
    private UUID id;

    private String description;
    private Integer score;
    private LocalDateTime createdAt;
    private boolean authorDeleted = false;

    @ManyToOne
    @JoinColumn(name = "author_id", nullable = true)
    private User author;

    @ManyToOne(optional = false)
    @JoinColumn(name = "film_id", nullable = false)
    private Film film;

    @OneToMany(mappedBy = "review", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Like> likes;

    public Review(String description, Integer score, User author, Film film, LocalDateTime createdAt) {
        this.description = description;
        this.score = score;
        this.createdAt = createdAt;
        this.author = author;
        this.film = film;
        this.likes = new ArrayList<Like>();
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public Film getFilm() {
        return film;
    }

    public void setFilm(Film film) {
        this.film = film;
    }

    public List<Like> getLikes() {
        return likes;
    }

    public void setLikes(List<Like> likes) {
        this.likes = likes;
    }

    public void setAuthorDeleted(boolean authorDeleted) { this.authorDeleted = authorDeleted;}
    public boolean isAuthorDeleted() { return authorDeleted; }


}
