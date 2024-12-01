package br.unitins.tp1.irondragon.dto.request;

import jakarta.validation.constraints.Email;

public record FabricanteRequestDTO(
        String nome,
        @Email
        String email,
        TelefoneRequestDTO telefone
) {
}
