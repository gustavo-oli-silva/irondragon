package br.unitins.tp1.irondragon.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CidadeRequestDTO (
        @NotBlank(message = "Nome da cidade não pode ser nulo!")
        String nome,
        @NotNull(message = "Estado não pode ser nulo")
        Long estado
) {
}
