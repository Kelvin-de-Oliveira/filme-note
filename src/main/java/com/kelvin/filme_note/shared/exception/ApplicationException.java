package com.kelvin.filme_note.shared.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ApplicationException extends RuntimeException{
    private final HttpStatus status;
    private final String code;

    public ApplicationException(String code, String message, HttpStatus status) {
        super(message);
        this.code = code;
        this.status = status;

    }

}
