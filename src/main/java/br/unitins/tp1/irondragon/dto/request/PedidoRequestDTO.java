package br.unitins.tp1.irondragon.dto.request;

import java.util.List;

public record PedidoRequestDTO (
        EnderecoRequestDTO endereco,
        List<ItemPedidoRequestDTO> listaItemPedido
) {
}
