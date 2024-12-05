package br.unitins.tp1.irondragon.dto.request.usuario;

import jakarta.validation.constraints.NotNull;

public record SalarioUpdateDTO(
        @NotNull(message = "Um salário válido deve ser informado")
        Double salario
) {
}
