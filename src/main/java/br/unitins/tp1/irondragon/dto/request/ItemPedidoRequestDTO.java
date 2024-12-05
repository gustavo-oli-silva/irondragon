package br.unitins.tp1.irondragon.dto.request;

import jakarta.validation.constraints.NotNull;

public record ItemPedidoRequestDTO (
        Long idProcessador,
        @NotNull(message = "quantidade não pode ser nula")
        Integer quantidade
) {
}
