package br.unitins.tp1.irondragon.model.pedido;

import br.unitins.tp1.irondragon.model.DefaultEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@Entity
public class ItemPedido extends DefaultEntity {
    @ManyToOne
    @JoinColumn(name = "id_lote")
    private Lote lote;

    private Integer quantidade;
    private Double preco;
}
