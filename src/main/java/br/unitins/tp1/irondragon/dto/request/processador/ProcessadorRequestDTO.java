package br.unitins.tp1.irondragon.dto.request.processador;

public record ProcessadorRequestDTO (
    String nome,
    String socket,
    Integer threads,
    Integer nucleos,
    Boolean desbloqueado,
    Double preco,
    Long fabricante,
    Long placaIntegrada,
    MemoriaCacheRequestDTO memoriaCache,
    FrequenciaRequestDTO frequencia,
    ConsumoEnergeticoRequestDTO consumoEnergetico,
    ConectividadeRequestDTO conectividade
) {
    
}
