package br.unitins.tp1.irondragon.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Cidade extends DefaultEntity {
    @Column(nullable = false)
    private String nome;
    @ManyToOne
    @JoinColumn(name = "id_estado")
    private Estado estado;
    public String getNome() {
        return nome;
    }
}
