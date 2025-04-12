package br.unitins.tp1.irondragon.dto.request.processador;

import br.unitins.tp1.irondragon.model.processador.Frequencia;
import jakarta.validation.constraints.NotNull;

public record FrequenciaRequestDTO(
     @NotNull(message = "O campo clockBasico deve ser informado")
        Double clockBasico,
        @NotNull(message = "O campo clockBoost deve ser informado")
        Double clockBoost
) {
    public Frequencia intoEntity() {
        Frequencia frequencia = new Frequencia();
        frequencia.setClockBasico(clockBasico());
        frequencia.setClockBoost(clockBoost());

        return frequencia;
    }
}
