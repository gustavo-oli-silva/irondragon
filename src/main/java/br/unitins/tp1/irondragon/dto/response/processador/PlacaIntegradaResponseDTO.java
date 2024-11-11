package br.unitins.tp1.irondragon.dto.response.processador;

import br.unitins.tp1.irondragon.model.processador.PlacaIntegrada;

public record PlacaIntegradaResponseDTO(
        Long id,
        String nome,
        Float directX,
        Float openGl,
        Float vulkan
) {
    public static PlacaIntegradaResponseDTO valueOf(PlacaIntegrada placaIntegrada) {
        return new PlacaIntegradaResponseDTO(
                placaIntegrada.getId(),
                placaIntegrada.getNome(),
                placaIntegrada.getDirectX(),
                placaIntegrada.getOpenGl(),
                placaIntegrada.getVulkan()
        );
    }
}