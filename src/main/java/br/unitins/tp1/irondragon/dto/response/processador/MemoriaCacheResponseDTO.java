package br.unitins.tp1.irondragon.dto.response.processador;

import br.unitins.tp1.irondragon.model.processador.MemoriaCache;

public record MemoriaCacheResponseDTO(
        Double cacheL2,
        Double cacheL3
) {
    public static MemoriaCacheResponseDTO valueOf(MemoriaCache memoriaCache) {
        return new MemoriaCacheResponseDTO(
                memoriaCache.getCacheL2(),
                memoriaCache.getCacheL3()
        );
    }
}
