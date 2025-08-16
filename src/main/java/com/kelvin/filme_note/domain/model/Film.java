package com.kelvin.filme_note.domain.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.util.List;
import java.util.UUID;

public class Film {

    @Id
    @GeneratedValue
    private UUID id;

    private String title;
    private String director;
    private Integer releaseYear;
    private String Genre;
    private List<Review> reviews;


}
