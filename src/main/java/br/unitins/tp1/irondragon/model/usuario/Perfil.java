package br.unitins.tp1.irondragon.model.usuario;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum Perfil {
    SUPER(1, "Super"),
    ADMIN(2, "Admin"),
    USER(3, "User");

    private final Integer id;
    private final String label;

    public Integer getId() {
        return id;
    }

    public String getLabel() {
        return label;
    }

    Perfil(Integer id, String label) {
        this.id = id;
        this.label = label;
    }


    public static Perfil valueOf(Integer id) {
        if(id == null) {
            return null;
        }

        for(Perfil perfil: Perfil.values()) {
            if(perfil.getId().equals(id)) {
                return perfil;
            }
        }

        throw new IllegalStateException();
    }
}
