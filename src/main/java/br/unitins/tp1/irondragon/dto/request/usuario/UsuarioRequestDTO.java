package br.unitins.tp1.irondragon.dto.request.usuario;

import br.unitins.tp1.irondragon.dto.request.TelefoneRequestDTO;
import jakarta.validation.constraints.Email;

import java.time.LocalDate;

public record UsuarioRequestDTO(
        String username,
        @Email
        String email,
        String senha,
        String cpf,
        LocalDate dataNascimento,
        TelefoneRequestDTO telefone
) {
}
