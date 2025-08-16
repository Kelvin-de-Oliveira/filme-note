package com.kelvin.filme_note.domain.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.time.LocalDateTime;
import java.util.UUID;

public class Like {

    @Id
    @GeneratedValue
    private UUID id;
    private LocalDateTime createdAt;
    private  User user;
    private Review review;
}
