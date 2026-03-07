package com.kelvin.filme_note.user.exception;

import com.kelvin.filme_note.shared.exception.ApplicationException;
import org.springframework.http.HttpStatus;

public class UserNotFoundApplicationException extends ApplicationException {
    public UserNotFoundApplicationException(){
        super("USER_NOT_FOUND", "User not found", HttpStatus.NOT_FOUND);
    }
}
