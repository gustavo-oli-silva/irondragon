package br.unitins.tp1.irondragon.dto.request.usuario;

import br.unitins.tp1.irondragon.dto.request.TelefoneRequestDTO;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;

public record UsuarioRequestDTO(
        @NotBlank(message = "username deve ser informado")
        String username,
        @Email
        String email,
        @NotBlank(message = "Uma senha deve ser fornecida")
        String senha,
        @CPF
        String cpf,
        @NotNull(message = "dataNascimento deve ser informada!")
        LocalDate dataNascimento,
        TelefoneRequestDTO telefone
) {
}
