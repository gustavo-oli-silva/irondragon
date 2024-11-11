package br.unitins.tp1.irondragon.dto.request.processador;

import br.unitins.tp1.irondragon.model.processador.ConsumoEnergetico;

public record ConsumoEnergeticoRequestDTO(
        Integer energiaBasica,
        Integer energiaMaxima
) {
    public ConsumoEnergetico intoEntity() {
        ConsumoEnergetico consumoEnergetico = new ConsumoEnergetico();
        consumoEnergetico.setEnergiaBasica(energiaBasica());
        consumoEnergetico.setEnergiaMaxima(energiaMaxima());

        return consumoEnergetico;
    }
}
