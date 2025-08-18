package com.kelvin.filme_note.service;

import com.kelvin.filme_note.domain.model.Film;
import com.kelvin.filme_note.domain.model.Role;
import com.kelvin.filme_note.domain.model.User;
import com.kelvin.filme_note.domain.repository.FilmRepository;
import com.kelvin.filme_note.domain.repository.ReviewRepository;
import org.springframework.stereotype.Service;

import java.time.Year;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class FilmService {

    private final FilmRepository filmRepository;
    private final ReviewRepository reviewRepository;

    public FilmService(FilmRepository filmRepository, ReviewRepository reviewRepository) {
        this.filmRepository = filmRepository;
        this.reviewRepository = reviewRepository;
    }

    // DTO para criação de filme
    public static class CreateFilmRequest {
        public String title;
        public String director;
        public Year releaseYear;
        public String genre;
    }

    // DTO para atualização de filme
    public static class UpdateFilmRequest {
        public String title;
        public String director;
        public Year releaseYear;
        public String genre;
    }

    public Film registerFilm(User user, CreateFilmRequest dto) {
        checkAdmin(user);
        Film film = new Film(dto.title, dto.director, dto.releaseYear, dto.genre);
        return filmRepository.save(film);
    }

    public Film updateFilm(User user, UUID filmId, UpdateFilmRequest dto) {
        checkAdmin(user);
        Film film = filmRepository.findById(filmId)
                .orElseThrow(() -> new IllegalArgumentException("Filme não encontrado"));

        if (dto.title != null) film.setTitle(dto.title);
        if (dto.director != null) film.setDirector(dto.director);
        if (dto.releaseYear != null) film.setReleaseYear(dto.releaseYear);
        if (dto.genre != null) film.setGenre(dto.genre);

        return filmRepository.save(film);
    }


    public void deleteFilm(User user, UUID filmId) {
        checkAdmin(user);
        reviewRepository.deleteByFilmId(filmId);
        filmRepository.deleteById(filmId);
    }

    public Optional<Film> findById(UUID filmId) {
        return filmRepository.findById(filmId);
    }

    public List<Film> findByTitle(String title) {
        return filmRepository.findByTitle(title);
    }

    private void checkAdmin(User user) {
        if (user.getRole() != Role.ADMIN) {
            throw new SecurityException("Acesso negado. Apenas administradores podem realizar esta ação.");
        }
    }
}
