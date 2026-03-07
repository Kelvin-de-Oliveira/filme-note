package com.kelvin.filme_note.like.exception;

import com.kelvin.filme_note.shared.exception.ApplicationException;
import org.springframework.http.HttpStatus;

public class LikeAlreadyExistsApplicationException extends ApplicationException {


    public LikeAlreadyExistsApplicationException() {
        super("LIKE_ALREADY_EXISTS", "like already exists", HttpStatus.CONFLICT);
    }
}
