package br.unitins.tp1.irondragon.dto.response.pagamento;

import br.unitins.tp1.irondragon.model.pagamento.Pix;

import java.time.LocalDateTime;

public record PixResponseDTO(
        Long id,
        String tipoPagamento,
        String chave,
        String destinatario,
        LocalDateTime dataValidade
) {
    public static PixResponseDTO valueOf(Pix pix) {

        return new PixResponseDTO(
                pix.getId(),
                "Pix",
                pix.getChave(),
                pix.getDestinatario(),
                pix.getDataValidade()
        );
    }
}
