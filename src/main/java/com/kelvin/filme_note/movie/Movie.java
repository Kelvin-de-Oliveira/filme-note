package com.kelvin.filme_note.movie;


import com.kelvin.filme_note.movie.enums.MovieGenres;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "movies")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String director;

    @Column(nullable = false)
    private Integer releaseYear;

    @ElementCollection(targetClass = MovieGenres.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "movie_genres", joinColumns = @JoinColumn(name = "movie_id"))
    @Enumerated(EnumType.STRING)
    @Column(name = "genre")
    @Builder.Default
    private Set<MovieGenres> genres = new HashSet<>();

    @Column(columnDefinition = "TEXT")
    private String synopsis;
}
