package br.unitins.tp1.irondragon.dto.request.usuario;

import jakarta.validation.constraints.NotBlank;

public record CargoUpdateDTO(
        @NotBlank(message = "Um cargo válido deve ser informado!")
        String cargo
) {
}
