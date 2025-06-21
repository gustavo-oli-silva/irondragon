package br.unitins.tp1.irondragon.listener;

import br.unitins.tp1.irondragon.model.processador.Processador;
import br.unitins.tp1.irondragon.service.lote.LoteService;
import jakarta.enterprise.inject.spi.CDI;
import jakarta.persistence.PostLoad;

public class ProcessadorListener {
    @PostLoad
    private void loadQuantidade(Processador processador) {
        LoteService loteService = CDI.current().select(LoteService.class).get();

        Integer quantidade = loteService.findEstoqueByIdProcessador(processador.getId());

        if(quantidade == null) {
            processador.setQuantidade(0);
            return;
        }

        processador.setQuantidade(quantidade);
    }
}
