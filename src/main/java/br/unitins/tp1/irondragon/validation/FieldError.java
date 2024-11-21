package br.unitins.tp1.irondragon.validation;

public record FieldError(
        String fieldName,
        String message
) {
}
