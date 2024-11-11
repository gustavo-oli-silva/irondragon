package br.unitins.tp1.irondragon.dto.response.processador;

import br.unitins.tp1.irondragon.model.processador.ConsumoEnergetico;

public record ConsumoEnergeticoResponseDTO(
        Integer energiaBasica,
        Integer energiaMaxima
) {
    public static ConsumoEnergeticoResponseDTO valueOf(ConsumoEnergetico consumoEnergetico) {
        return new ConsumoEnergeticoResponseDTO(
                consumoEnergetico.getEnergiaBasica(),
                consumoEnergetico.getEnergiaMaxima()
        );
    }
}
