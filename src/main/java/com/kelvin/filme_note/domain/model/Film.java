package com.kelvin.filme_note.domain.model;

import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "tab_film")
public class Film {

    @Id
    @GeneratedValue
    private UUID id;

    private String title;
    private String director;
    private Integer releaseYear;
    private String Genre;

    @OneToMany(mappedBy = "film")
    private List<Review> reviews;


}
