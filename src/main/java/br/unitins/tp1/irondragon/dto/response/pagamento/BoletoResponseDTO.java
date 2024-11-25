package br.unitins.tp1.irondragon.dto.response.pagamento;

import br.unitins.tp1.irondragon.model.pagamento.Boleto;

import java.time.LocalDate;

public record BoletoResponseDTO(
        Long id,
        String codigoBarras,
        LocalDate dataValidade
) {
    public static BoletoResponseDTO valueOf(Boleto boleto) {
        return new BoletoResponseDTO(
                boleto.getId(),
                boleto.getCodigoBarras(),
                boleto.getDataValidade()
        );
    }
}
