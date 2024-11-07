package br.unitins.tp1.irondragon.dto.response;

import br.unitins.tp1.irondragon.model.pedido.ItemPedido;

public record ItemPedidoResponseDTO (
       Long idProcessador,
       String nome,
       Integer quantidade,
       Double valor
) {
    public static ItemPedidoResponseDTO valueOf(ItemPedido itemPedido) {
        return new ItemPedidoResponseDTO (
                itemPedido.getLote().getProcessador().getId(),
                itemPedido.getLote().getProcessador().getNome(),
                itemPedido.getQuantidade(),
                itemPedido.getPreco()
        );
    }
}
