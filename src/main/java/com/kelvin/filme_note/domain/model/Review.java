package com.kelvin.filme_note.domain.model;

import jakarta.persistence.*;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;


import java.time.LocalDateTime;
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

    @ManyToOne
    @JoinColumn(name = "author_id", nullable = true)
    private User author;

    @ManyToOne(optional = false)
    @JoinColumn(name = "film_id", nullable = false)
    private Film film;

    @OneToMany(mappedBy = "review", cascade = CascadeType.ALL, orphanRemoval = true)
    private Like likes;

}
