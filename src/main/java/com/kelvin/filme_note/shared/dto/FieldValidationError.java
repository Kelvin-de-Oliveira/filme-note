package com.kelvin.filme_note.shared.dto;

public record FieldValidationError(
        String field,
        String message
) {
}
