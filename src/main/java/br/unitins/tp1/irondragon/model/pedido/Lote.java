package br.unitins.tp1.irondragon.model.pedido;

import br.unitins.tp1.irondragon.model.DefaultEntity;
import br.unitins.tp1.irondragon.model.processador.Processador;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
public class Lote extends DefaultEntity {
    @ManyToOne
    @JoinColumn(name = "id_processador")
    private Processador processador;
    private LocalDate data;
    private String codigo;
    private Integer estoque;
}
