package br.unitins.tp1.irondragon.dto.request;

public record ItemPedidoRequestDTO (
        Long idProcessador,
        Integer quantidade,
        Double preco
) {
}
