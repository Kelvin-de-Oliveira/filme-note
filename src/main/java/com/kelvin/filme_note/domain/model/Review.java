package com.kelvin.filme_note.domain.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import java.time.LocalDateTime;
import java.util.UUID;

public class Review {
    @Id
    @GeneratedValue
    private UUID id;

    private String description;
    private Integer score;
    private LocalDateTime createdAt;

    private User author;
    private Film film;
    private Like likes;

}
