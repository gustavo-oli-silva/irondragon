package br.unitins.tp1.irondragon.dto.response.pagamento;

import br.unitins.tp1.irondragon.model.pagamento.Pix;

import java.time.LocalDateTime;

public record PixResponseDTO(
        Long id,
        Double valor,
        String tipoPagamento,
        String chave,
        String destinatario,
        LocalDateTime dataValidade,
        Boolean pago
) {
    public static PixResponseDTO valueOf(Pix pix) {

        return new PixResponseDTO(
                pix.getId(),
                pix.getValor(),
                "Pix",
                pix.getChave(),
                pix.getDestinatario(),
                pix.getDataValidade(),
                pix.getPago()
        );
    }
}
