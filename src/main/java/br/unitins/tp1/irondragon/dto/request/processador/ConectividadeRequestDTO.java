package br.unitins.tp1.irondragon.dto.request.processador;

import br.unitins.tp1.irondragon.model.processador.Conectividade;
import jakarta.validation.constraints.NotNull;

public record ConectividadeRequestDTO(
        @NotNull(message = "O campo pci deve ser informado")
        Float pci,
        @NotNull(message = "O campo tipoMemoria deve ser informado")
        String tipoMemoria,
        @NotNull(message = "O campo canaisMemoria deve ser informado")
        Integer canaisMemoria
) {
    public Conectividade intoEntity() {
        Conectividade conectividade = new Conectividade();

        conectividade.setPci(pci());
        conectividade.setTipoMemoria(tipoMemoria());
        conectividade.setCanaisMemoria(canaisMemoria());

        return conectividade;
    }
}
