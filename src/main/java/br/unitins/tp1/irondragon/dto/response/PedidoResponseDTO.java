package br.unitins.tp1.irondragon.dto.response;

import br.unitins.tp1.irondragon.model.pagamento.Pagamento;
import br.unitins.tp1.irondragon.model.pedido.Pedido;

import java.time.LocalDateTime;
import java.util.List;

public record PedidoResponseDTO(
        Long id,
        LocalDateTime data,
        Double valorTotal,
        EnderecoResponseDTO endereco,
        List<ItemPedidoResponseDTO> listaItemPedido
) {
    public static PedidoResponseDTO valueOf(Pedido pedido) {
        return new PedidoResponseDTO(
                pedido.getId(),
                pedido.getData(),
                pedido.getValorTotal(),
                EnderecoResponseDTO.valueOf(pedido.getEnderecoEntrega()),
                pedido.getListaItemPedido().stream().map(ItemPedidoResponseDTO::valueOf).toList()
        );
    }
}
