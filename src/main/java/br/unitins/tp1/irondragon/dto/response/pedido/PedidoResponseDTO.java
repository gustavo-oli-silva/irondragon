package br.unitins.tp1.irondragon.dto.response.pedido;

import br.unitins.tp1.irondragon.dto.response.EnderecoResponseDTO;
import br.unitins.tp1.irondragon.dto.response.ItemPedidoResponseDTO;
import br.unitins.tp1.irondragon.dto.response.pagamento.BoletoResponseDTO;
import br.unitins.tp1.irondragon.dto.response.pagamento.CartaoPagamentoResponseDTO;
import br.unitins.tp1.irondragon.dto.response.pagamento.PixResponseDTO;
import br.unitins.tp1.irondragon.model.pagamento.Boleto;
import br.unitins.tp1.irondragon.model.pagamento.CartaoPagamento;
import br.unitins.tp1.irondragon.model.pagamento.Pix;
import br.unitins.tp1.irondragon.model.pedido.Pedido;
import br.unitins.tp1.irondragon.model.pedido.StatusPedido;

import java.time.LocalDateTime;
import java.util.List;

public record PedidoResponseDTO<FormaPagamento> (
        Long id,
        LocalDateTime data,
        Double valorTotal,
        StatusPedido statusPedido,
        FormaPagamento pagamento,
        EnderecoResponseDTO endereco,
        List<ItemPedidoResponseDTO> listaItemPedido
) {
    public static PedidoResponseDTO valueOf(Pedido pedido) {
        if(pedido.getPagamento() instanceof Pix) {
            return new PedidoResponseDTO(
                    pedido.getId(),
                    pedido.getData(),
                    pedido.getValorTotal(),
                    pedido.getStatusPedido(),
                    PixResponseDTO.valueOf((Pix) pedido.getPagamento()),
                    EnderecoResponseDTO.valueOf(pedido.getEnderecoEntrega()),
                    pedido.getListaItemPedido().stream().map(ItemPedidoResponseDTO::valueOf).toList()
            );
        } else if(pedido.getPagamento() instanceof Boleto) {
            return new PedidoResponseDTO(
                    pedido.getId(),
                    pedido.getData(),
                    pedido.getValorTotal(),
                    pedido.getStatusPedido(),
                    BoletoResponseDTO.valueOf((Boleto) pedido.getPagamento()),
                    EnderecoResponseDTO.valueOf(pedido.getEnderecoEntrega()),
                    pedido.getListaItemPedido().stream().map(ItemPedidoResponseDTO::valueOf).toList()
            );
        } else if (pedido.getPagamento() instanceof CartaoPagamento) {
            return new PedidoResponseDTO(
                    pedido.getId(),
                    pedido.getData(),
                    pedido.getValorTotal(),
                    pedido.getStatusPedido(),
                    CartaoPagamentoResponseDTO.valueOf((CartaoPagamento) pedido.getPagamento()),
                    EnderecoResponseDTO.valueOf(pedido.getEnderecoEntrega()),
                    pedido.getListaItemPedido().stream().map(ItemPedidoResponseDTO::valueOf).toList()
            );
        } else {
            return new PedidoResponseDTO(
                    pedido.getId(),
                    pedido.getData(),
                    pedido.getValorTotal(),
                    pedido.getStatusPedido(),
                    pedido.getPagamento(),
                    EnderecoResponseDTO.valueOf(pedido.getEnderecoEntrega()),
                    pedido.getListaItemPedido().stream().map(ItemPedidoResponseDTO::valueOf).toList()
            );
        }
    }
}
