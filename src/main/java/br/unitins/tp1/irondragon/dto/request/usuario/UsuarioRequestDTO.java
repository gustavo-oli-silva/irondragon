package br.unitins.tp1.irondragon.dto.request.usuario;

import jakarta.validation.constraints.Email;

import java.time.LocalDate;

public record UsuarioRequestDTO(
        String username,
        @Email
        String email,
        String senha,
        LocalDate dataNascimento
) {
}
