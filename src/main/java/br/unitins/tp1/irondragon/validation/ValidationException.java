package br.unitins.tp1.irondragon.validation;

import lombok.Getter;

@Getter
public class ValidationException extends RuntimeException {
    private String fieldName;

    public ValidationException(String fieldName, String message) {
        super(message);
        this.fieldName = fieldName;
    }
}
