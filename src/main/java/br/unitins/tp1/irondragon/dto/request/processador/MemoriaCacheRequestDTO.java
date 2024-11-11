package br.unitins.tp1.irondragon.dto.request.processador;

import br.unitins.tp1.irondragon.model.processador.MemoriaCache;

public record MemoriaCacheRequestDTO(
        Double cacheL2,
        Double cacheL3
) {
    public MemoriaCache intoEntity() {
        MemoriaCache memoriaCache = new MemoriaCache();
        memoriaCache.setCacheL2(cacheL2());
        memoriaCache.setCacheL3(cacheL3());

        return memoriaCache;
    }
}
