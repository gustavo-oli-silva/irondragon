package br.unitins.tp1.irondragon.validation;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Error {
    private Integer code;
    private String message;

    public Error (Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
