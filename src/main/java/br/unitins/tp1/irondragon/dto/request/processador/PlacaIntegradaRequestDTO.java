package br.unitins.tp1.irondragon.dto.request.processador;

import jakarta.validation.constraints.NotBlank;

public record PlacaIntegradaRequestDTO(
        @NotBlank(message = "Nome da Placa Integrada não pode ser nulo!")
        String nome,
        Float directX,
        Float openGl,
        Float vulkan
) {
}
