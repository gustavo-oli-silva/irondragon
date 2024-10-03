package br.unitins.tp1.irondragon.dto;

import jakarta.validation.constraints.NotNull;

public record CidadeRequestDTO (
    String nome,
    @NotNull(message = "Estado não pode ser nulo")
    Long estado
) {
}
