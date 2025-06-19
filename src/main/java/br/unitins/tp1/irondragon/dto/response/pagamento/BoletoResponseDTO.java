package br.unitins.tp1.irondragon.dto.response.pagamento;

import br.unitins.tp1.irondragon.model.pagamento.Boleto;

import java.time.LocalDate;

public record BoletoResponseDTO(
        Long id,
        Double valor,
        String tipoPagamento,
        String codigoBarras,
        LocalDate dataValidade,
        Boolean pago
) {
    public static BoletoResponseDTO valueOf(Boleto boleto) {
        return new BoletoResponseDTO(
                boleto.getId(),
                boleto.getValor(),
                "Boleto",
                boleto.getCodigoBarras(),
                boleto.getDataValidade(),
                boleto.getPago()
        );
    }
}
