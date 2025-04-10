package br.unitins.tp1.irondragon.dto.request.processador;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ProcessadorRequestDTO (
    @NotBlank(message = "O campo nome deve ser informado")
    String nome,
    @NotBlank(message = "O campo socket deve ser informado")
    String socket,
    @NotNull(message = "O campo threads deve ser informado")
    Integer threads,
    @NotNull(message = "O campo nucleos deve ser informado")
    Integer nucleos,
    @NotNull(message = "O campo desbloqueado deve ser informado")
    Boolean desbloqueado,
    @NotNull(message = "O campo preço deve ser informado")
    Double preco,
    @NotNull(message = "O fabricante preço deve ser informado")
    Long fabricante,
    @NotNull(message = "O campo placaIntegrada deve ser informado")
    Long placaIntegrada,
    MemoriaCacheRequestDTO memoriaCache,
    FrequenciaRequestDTO frequencia,
    ConsumoEnergeticoRequestDTO consumoEnergetico,
    ConectividadeRequestDTO conectividade
) {
    
}
