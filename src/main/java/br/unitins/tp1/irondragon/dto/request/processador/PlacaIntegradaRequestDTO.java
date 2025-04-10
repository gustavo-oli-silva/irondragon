package br.unitins.tp1.irondragon.dto.request.processador;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PlacaIntegradaRequestDTO(
        @NotBlank(message = "Nome da Placa Integrada não pode ser nulo!")
        String nome,
        @NotNull(message = "O campo placaIntegrada deve ser informado")
        Float directX,
        @NotNull(message = "O campo openGl deve ser informado")
        Float openGl,
        @NotNull(message = "O campo vulkan deve ser informado")
        Float vulkan
) {
}
