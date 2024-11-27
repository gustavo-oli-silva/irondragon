package br.unitins.tp1.irondragon.model.pedido;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;

@Getter
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum StatusPedido {
    PEDIDO_EXPIRADO (1, "Pedido expirado"),
    PAGAMENTO_PENDENTE (2, "Pagamento pendente"),
    PREPARANDO_PRODUTO (3, "Preparando produto"),
    PRODUTO_ENVIADO (4, "Produto enviado"),
    PRODUTO_ENTREGUE (5, "Produto entregue");

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
