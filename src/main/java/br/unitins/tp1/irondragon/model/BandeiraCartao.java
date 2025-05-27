package br.unitins.tp1.irondragon.model;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum BandeiraCartao {
    VISA(1, "Visa"),
    MASTERCARD(2, "Mastercard"),
    ELO(3, "Elo"),
    AMEX(4, "American Express"),
    HIPERCARD(5, "Hipercard"),
    DINERS(6, "Diners Club"),
    DISCOVER(7, "Discover"),
    DESCONHECIDA(99, "Desconhecida");

    private final Integer id;
    private final String label;

    BandeiraCartao(Integer id, String label) {
        this.id = id;
        this.label = label;
    }

    public Integer getId() {
        return id;
    }

    public String getLabel() {
        return label;
    }

    public static BandeiraCartao valueOf(Integer id) {
        if (id == null) return null;

        for (BandeiraCartao bandeira : BandeiraCartao.values()) {
            if (bandeira.getId().equals(id)) {
                return bandeira;
            }
        }

        return DESCONHECIDA;
    }
}