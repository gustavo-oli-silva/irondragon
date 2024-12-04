package br.unitins.tp1.irondragon.dto.request.usuario;

import jakarta.validation.constraints.Email;

public record EmailUpdateDTO(
        @Email
        String email
) {
}
