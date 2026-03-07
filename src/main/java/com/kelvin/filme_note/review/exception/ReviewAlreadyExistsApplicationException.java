package com.kelvin.filme_note.review.exception;

import com.kelvin.filme_note.shared.exception.ApplicationException;
import org.springframework.http.HttpStatus;

public class ReviewAlreadyExistsApplicationException extends ApplicationException {

    public ReviewAlreadyExistsApplicationException(){
        super("REVIEW_ALREADY_EXISTS", "review already exists", HttpStatus.CONFLICT);
    }
}
