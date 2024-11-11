package br.unitins.tp1.irondragon.dto.response.processador;

import br.unitins.tp1.irondragon.model.processador.Conectividade;

public record ConectividadeResponseDTO(
        Float pci,
        String tipoMemoria,
        Integer canaisMemoria
) {
    public static ConectividadeResponseDTO valueOf(Conectividade conectividade) {
        return new ConectividadeResponseDTO(
                conectividade.getPci(),
                conectividade.getTipoMemoria(),
                conectividade.getCanaisMemoria()
        );
    }
}
