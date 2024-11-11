package br.unitins.tp1.irondragon.dto.request.processador;

import br.unitins.tp1.irondragon.model.processador.Conectividade;

public record ConectividadeRequestDTO(
        Float pci,
        String tipoMemoria,
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
