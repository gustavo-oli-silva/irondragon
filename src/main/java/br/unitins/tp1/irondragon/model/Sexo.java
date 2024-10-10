package br.unitins.tp1.irondragon.model;


import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Objects;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum Sexo {
    MASCULINO (1, "Masculino"),
    FEMININO (2, "Feminino");

    private final Integer id;
    private final String label;

    Sexo(Integer id, String label) {
        this.id = id;
        this.label = label;
    }

    public Integer getId() {
        return id;
    }

    public String getLabel() {
        return label;
    }

    public static Sexo valueOf(Integer id) {
        if(id == null) {
            return null;
        }

        for(Sexo sexo: Sexo.values()) {
            if(Objects.equals(sexo.id, id)) {
                return sexo;
            }
        }

        throw new IllegalArgumentException("Id inválido!");
    }
}
