package br.unitins.tp1.irondragon.service.pagamento;

import br.unitins.tp1.irondragon.model.Cartao;
import br.unitins.tp1.irondragon.model.pagamento.Boleto;
import br.unitins.tp1.irondragon.model.pagamento.CartaoPagamento;
import br.unitins.tp1.irondragon.model.pagamento.Pagamento;
import br.unitins.tp1.irondragon.model.pagamento.Pix;
import jakarta.transaction.Transactional;

public interface PagamentoService {
    Pagamento findById(Long id);

    Pagamento findByIdPedido(Long idPedido);

    CartaoPagamento cardPayment(Long idPedido, long idCartao, String username);

    Boleto generateBoleto(Long idPedido, String username);

    Pix generatePix(Long idPedido, String username);

    void payment(Long idPedido, Long idPagamento, String username, String tipoPagamento);
}
