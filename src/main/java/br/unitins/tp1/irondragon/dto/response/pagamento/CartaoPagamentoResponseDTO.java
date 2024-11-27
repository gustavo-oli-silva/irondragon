package br.unitins.tp1.irondragon.dto.response.pagamento;

import br.unitins.tp1.irondragon.model.Cartao;
import br.unitins.tp1.irondragon.model.TipoCartao;
import br.unitins.tp1.irondragon.model.pagamento.CartaoPagamento;

import java.time.LocalDate;

public record CartaoPagamentoResponseDTO(
        Long id,
        String tipoPagamento,
        String nomeTitular,
        String numero,
        String cpf,
        LocalDate validade,
        Integer cvc,
        TipoCartao tipo
) {
    public static CartaoPagamentoResponseDTO valueOf(CartaoPagamento cartao) {
        return new CartaoPagamentoResponseDTO(
                cartao.getId(),
                "Cartão",
                cartao.getNomeTitular(),
                cartao.getNumero(),
                cartao.getCpf(),
                cartao.getValidade(),
                cartao.getCvc(),
                cartao.getTipo()
        );
    }
}