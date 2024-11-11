package br.unitins.tp1.irondragon.model.pedido;

import br.unitins.tp1.irondragon.model.DefaultEntity;
import br.unitins.tp1.irondragon.model.usuario.Usuario;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
public class Pedido extends DefaultEntity {
    private LocalDateTime data;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "id_pedido")
    private List<ItemPedido> listaItemPedido;
    private Double valorTotal;
}
