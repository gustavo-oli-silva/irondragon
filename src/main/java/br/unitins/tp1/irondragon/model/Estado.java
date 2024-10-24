package br.unitins.tp1.irondragon.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Estado extends DefaultEntity {
    @Column(length = 60, nullable = false)
    private String nome;

    @Column(length = 2, nullable = false)
    private String sigla;
}
