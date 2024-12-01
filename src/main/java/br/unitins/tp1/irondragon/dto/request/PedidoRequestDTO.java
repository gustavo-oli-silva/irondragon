package br.unitins.tp1.irondragon.dto.request;

import java.util.List;

public record PedidoRequestDTO (
        Long idEndereco,
        List<ItemPedidoRequestDTO> listaItemPedido
) {
}
