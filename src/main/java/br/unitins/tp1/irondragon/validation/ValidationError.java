package br.unitins.tp1.irondragon.validation;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class ValidationError extends Error {
    @Getter
    private List<FieldError> errors = null;

    public ValidationError(Integer code, String message) {
        super(code, message);
    }

    public void addFieldError(String fieldName, String message) {
        if(errors == null) {
            errors = new ArrayList<FieldError>();
        }

        errors.add(new FieldError(fieldName, message));
    }
}
