
package br.unitins.tp1.irondragon.dto.request.usuario;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record FuncionarioRequestDTO(

        @NotNull(message = "A data deve ser informada")
        LocalDate dataContratacao,
        @NotBlank(message = "O cargo deve ser informado")
        String cargo,
        @NotNull(message = "O salario deve ser informado")
        Double salario
) {
}
