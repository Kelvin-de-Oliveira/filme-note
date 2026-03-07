package com.kelvin.filme_note.movie.exception;

import com.kelvin.filme_note.shared.exception.ApplicationException;
import org.springframework.http.HttpStatus;

public class MovieNotFoundApplicationException extends ApplicationException {
    public MovieNotFoundApplicationException() {
      super("MOVIE_NOT_FOUND", "Movie not found", HttpStatus.NOT_FOUND);
    }
}
