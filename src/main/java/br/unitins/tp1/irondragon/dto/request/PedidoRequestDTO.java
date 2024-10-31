package br.unitins.tp1.irondragon.dto.request;

import java.util.List;

public record PedidoRequestDTO (
        Double valorTotal,
        List<ItemPedidoRequestDTO> listaItemPedido
) {
}
