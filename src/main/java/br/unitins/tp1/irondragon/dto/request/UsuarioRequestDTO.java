package br.unitins.tp1.irondragon.dto.request;

import br.unitins.tp1.irondragon.model.Perfil;
import jakarta.validation.constraints.Email;

public record UsuarioRequestDTO(
        String username,
        @Email
        String email,
        String senha
) {
}
