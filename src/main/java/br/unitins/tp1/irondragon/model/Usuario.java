package br.unitins.tp1.irondragon.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Usuario extends DefaultEntity {
    @Column(unique = true)
    private String username;
    @Column(unique = true)
    private String email;
    private String senha;
    private Perfil perfil;
}
