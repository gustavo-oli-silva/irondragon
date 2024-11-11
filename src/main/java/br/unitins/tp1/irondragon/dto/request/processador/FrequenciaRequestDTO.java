package br.unitins.tp1.irondragon.dto.request.processador;

import br.unitins.tp1.irondragon.model.processador.Frequencia;

public record FrequenciaRequestDTO(
        Double clockBasico,
        Double clockBoost
) {
    public Frequencia intoEntity() {
        Frequencia frequencia = new Frequencia();
        frequencia.setClockBasico(clockBasico());
        frequencia.setClockBoost(clockBoost());

        return frequencia;
    }
}
