package br.unitins.tp1.irondragon.dto.request;

public record PlacaIntegradaRequestDTO(
        String nome,
        Float directX,
        Float openGl,
        Float vulkan
) {
}
