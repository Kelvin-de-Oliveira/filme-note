package com.kelvin.filme_note.dto.review;

import java.util.UUID;

public class CreateReviewRequest {
    public UUID filmId;
    public String description;
    public int score;
}