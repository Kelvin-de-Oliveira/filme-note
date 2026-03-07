package com.kelvin.filme_note.shared.dto;

import java.time.Instant;
import java.util.List;

public record ErrorResponse(String code,
                            String message,
                            int status,
                            String path,
                            Instant timestamp,
                            List<FieldValidationError> fieldErrors) {

}
