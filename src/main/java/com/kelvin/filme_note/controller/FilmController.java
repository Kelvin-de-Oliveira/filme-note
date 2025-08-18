package com.kelvin.filme_note.controller;

import com.kelvin.filme_note.domain.model.Film;
import com.kelvin.filme_note.domain.model.User;
import com.kelvin.filme_note.service.FilmService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/films")
public class FilmController {

    private final FilmService filmService;

    public FilmController(FilmService filmService) {
        this.filmService = filmService;
    }

    @PostMapping("/register")
    public ResponseEntity<Film> create(@RequestAttribute("user") User user,
                                       @RequestBody FilmService.CreateFilmRequest dto) {
        return ResponseEntity.ok(filmService.registerFilm(user, dto));
    }

    @PutMapping("update/{filmId}")
    public ResponseEntity<Film> update(@RequestAttribute("user") User user,
                                       @PathVariable UUID filmId,
                                       @RequestBody FilmService.UpdateFilmRequest dto) {
        return ResponseEntity.ok(filmService.updateFilm(user, filmId, dto));
    }

    @DeleteMapping("delete/{filmId}")
    public ResponseEntity<Void> delete(@RequestAttribute("user") User user,
                                       @PathVariable UUID filmId) {
        filmService.deleteFilm(user, filmId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("searchById/{filmId}")
    public ResponseEntity<Film> getById(@PathVariable UUID filmId) {
        return filmService.findById(filmId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/searchByName")
    public ResponseEntity<List<Film>> searchByTitle(@RequestParam String title) {
        return ResponseEntity.ok(filmService.findByTitle(title));
    }
}
