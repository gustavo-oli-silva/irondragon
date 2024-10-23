package br.unitins.tp1.irondragon.model;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum TipoCartao {
    CREDITO (1, "Crédito"),
    DEBITO (2, "Débito");

    private final Integer id;
    private final String label;

    TipoCartao(Integer id, String label) {
        this.id = id;
        this.label = label;
    }

    public Integer getId() {
        return id;
    }

    public String getLabel() {
        return label;
    }

    public static TipoCartao valueOf(Integer id) {
        if(id == null) {
            return null;
        }

        for(TipoCartao tipoCartao: TipoCartao.values()) {
            if(tipoCartao.getId().equals(id)) {
                return tipoCartao;
            }
        }

        throw new IllegalArgumentException("Cartão Inválido!");
    }
}
