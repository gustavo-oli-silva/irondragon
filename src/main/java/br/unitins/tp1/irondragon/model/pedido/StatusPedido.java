package br.unitins.tp1.irondragon.model.pedido;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;

@Getter
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum StatusPedido {
    PEDIDO_EXPIRADO (1, "Pedido expirado"),
    PEDIDO_CANCELADO( 2, "Pedido cancelado"),
    PAGAMENTO_PENDENTE (3, "Pagamento pendente"),
    PREPARANDO_PRODUTO (4, "Preparando produto"),
    PRODUTO_ENVIADO (5, "Produto enviado"),
    PRODUTO_ENTREGUE (6, "Produto entregue"),
    PRODUTO_DEVOLVIDO(7, "Produto devolvido!");

    private final Integer id;
    private final String label;

    StatusPedido(Integer id, String label) {
        this.id = id;
        this.label = label;
    }

    public static StatusPedido valueOf(Integer id) {
        if(id == null) {
            return null;
        }

        for(StatusPedido statusPedido: StatusPedido.values()) {
            if(statusPedido.getId().equals(id)) {
                return statusPedido;
            }
        }

        throw new IllegalStateException();
    }
}
