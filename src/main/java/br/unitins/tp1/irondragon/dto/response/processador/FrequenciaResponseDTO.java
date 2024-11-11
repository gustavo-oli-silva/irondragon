package br.unitins.tp1.irondragon.dto.response.processador;

import br.unitins.tp1.irondragon.model.processador.Frequencia;

public record FrequenciaResponseDTO(
        Double clockBasico,
        Double clockBoost
) {
    public static FrequenciaResponseDTO valueOf(Frequencia frequencia) {
        return new FrequenciaResponseDTO(
                frequencia.getClockBasico(),
                frequencia.getClockBoost()
        );
    }
}
