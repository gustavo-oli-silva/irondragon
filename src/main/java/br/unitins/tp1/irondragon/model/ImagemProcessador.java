package br.unitins.tp1.irondragon.model;

import br.unitins.tp1.irondragon.model.processador.Processador;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class ImagemProcessador extends DefaultEntity {

    @Column(nullable = false)
    private String nomeImagem;

    @Column(nullable = false)
    private String imagem;

    @Column(nullable = false)
    private boolean principal;

    private int index;

}
