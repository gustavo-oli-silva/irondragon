package br.unitins.tp1.irondragon.dto.request;

public record PedidoRequestDTO (
        Double valorTotal,
        List<ItemPedidoRequestDTO> listaItemPedido
) {
}
