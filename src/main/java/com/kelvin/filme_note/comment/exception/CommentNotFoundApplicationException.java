package com.kelvin.filme_note.comment.exception;

import com.kelvin.filme_note.shared.exception.ApplicationException;
import org.springframework.http.HttpStatus;

public class CommentNotFoundApplicationException extends ApplicationException {
    public CommentNotFoundApplicationException() {
        super("COMMENT_NOT_FOUND", "Comment not found", HttpStatus.NOT_FOUND);
    }
}
