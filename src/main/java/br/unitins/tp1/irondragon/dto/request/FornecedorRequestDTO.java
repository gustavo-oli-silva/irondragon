package br.unitins.tp1.irondragon.dto.request;

import jakarta.validation.constraints.Email;

public record FornecedorRequestDTO(
        String nome,
        @Email
        String email
) {
}
