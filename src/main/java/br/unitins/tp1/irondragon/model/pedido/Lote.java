package br.unitins.tp1.irondragon.model.pedido;

import br.unitins.tp1.irondragon.model.DefaultEntity;
import br.unitins.tp1.irondragon.model.Fornecedor;
import br.unitins.tp1.irondragon.model.processador.Processador;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@Entity
public class Lote extends DefaultEntity {
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_processador")
    private Processador processador;
    private LocalDate data;
    @Column(unique = true)
    private String codigo;
    private Integer estoque;

    @ManyToOne
    @JoinColumn(name = "id_fornecedor")
    private Fornecedor fornecedor;
}
