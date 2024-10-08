package br.unitins.tp1.irondragon.dto;

import jakarta.validation.constraints.Email;

public record FabricanteRequestDTO(
        String nome,
        @Email
        String email
) {
}
