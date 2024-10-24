package br.unitins.tp1.irondragon.dto.request;

public record ItemPedidoRequestDTO (
        Long idProduto,
        Integer quantidade,
        Double preco
) {
}
