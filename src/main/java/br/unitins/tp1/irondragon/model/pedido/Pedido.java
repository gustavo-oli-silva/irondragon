package br.unitins.tp1.irondragon.model.pedido;

import br.unitins.tp1.irondragon.model.DefaultEntity;
import br.unitins.tp1.irondragon.model.pagamento.Pagamento;
import br.unitins.tp1.irondragon.model.usuario.Cliente;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@Entity
public class Pedido extends DefaultEntity {
    private LocalDateTime data;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_cliente")
    private Cliente cliente;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_enderecoentrega")
    private EnderecoEntrega enderecoEntrega;

    @ManyToOne(cascade = CascadeType.ALL)
    private Pagamento pagamento;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "id_pedido")
    private List<ItemPedido> listaItemPedido;
    private Double valorTotal;

    private StatusPedido statusPedido;
}
