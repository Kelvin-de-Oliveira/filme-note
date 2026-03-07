package com.kelvin.filme_note.shared.exception;


import com.kelvin.filme_note.shared.dto.ErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ApplicationException.class)
    public final ResponseEntity<ErrorResponse> handleCustomException(ApplicationException ex, HttpServletRequest request) {
        ErrorResponse error = new ErrorResponse(
                ex.getCode(),
                ex.getMessage(),
                ex.getStatus().value(),
                request.getRequestURI(),
                Instant.now(),
                null
        );
        return ResponseEntity.status(ex.getStatus()).body(error);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleUnexpectedException(
            Exception ex,
            HttpServletRequest request
    ) {
        ErrorResponse error = new ErrorResponse(
                "INTERNAL_SERVER_ERROR",
                "An unexpected internal error has occurred",
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                request.getRequestURI(),
                Instant.now(),
                null
        );
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }
}
