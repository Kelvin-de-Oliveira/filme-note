package com.kelvin.filme_note.review.exception;

import com.kelvin.filme_note.shared.exception.ApplicationException;
import org.springframework.http.HttpStatus;

public class ReviewNotFoundApplicationException extends ApplicationException {
    public ReviewNotFoundApplicationException() {
        super("REVIEW_NOT_FOUND", "Review not found", HttpStatus.NOT_FOUND);
    }
}
