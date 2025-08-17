package com.kelvin.filme_note.domain.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.Year;
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
    private Year releaseYear;
    private String Genre;

    @OneToMany(mappedBy = "film")
    private List<Review> reviews;

    public Film(String title, String director, Year releaseYear, String genre) {
        this.title = title;
        this.director = director;
        this.releaseYear = releaseYear;
        Genre = genre;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public Year getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(Year releaseYear) {
        this.releaseYear = releaseYear;
    }

    public String getGenre() {
        return Genre;
    }

    public void setGenre(String genre) {
        Genre = genre;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }
}
