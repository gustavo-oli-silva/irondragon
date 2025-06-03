package br.unitins.tp1.irondragon.dto.response.pedido;

import br.unitins.tp1.irondragon.dto.response.ItemPedidoResponseDTO;
import br.unitins.tp1.irondragon.model.pagamento.Boleto;
import br.unitins.tp1.irondragon.model.pagamento.CartaoPagamento;
import br.unitins.tp1.irondragon.model.pagamento.Pix;
import br.unitins.tp1.irondragon.model.pedido.Pedido;
import br.unitins.tp1.irondragon.model.pedido.StatusPedido;

import java.time.LocalDateTime;
import java.util.List;

public record PedidoBasicResponseDTO(
        Long id,
        LocalDateTime data,
        Double valorTotal,
        String formaPagamento,
        StatusPedido statusPedido,
        List<ItemPedidoResponseDTO> listaItemPedido) {
    public static PedidoBasicResponseDTO valueOf(Pedido pedido) {
        String formaPagamento = "Sem cadastro";

        if (pedido.getPagamento() instanceof CartaoPagamento) {
            formaPagamento = "Cartão";
        } else if (pedido.getPagamento() instanceof Pix) {
            formaPagamento = "Pix";
        } else if (pedido.getPagamento() instanceof Boleto) {
            formaPagamento = "Boleto";
        }

        return new PedidoBasicResponseDTO(
                pedido.getId(),
                pedido.getData(),
                pedido.getValorTotal(),
                formaPagamento,
                pedido.getStatusPedido(),
                pedido.getListaItemPedido().stream().map(ItemPedidoResponseDTO::valueOf).toList());
    }
}
