package br.unitins.tp1.irondragon.dto.request.usuario;

import org.hibernate.validator.constraints.br.CPF;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UsuarioRequestDTO(
        @NotBlank(message = "username deve ser informado")
        String username,
        @NotBlank(message = "email deve ser informado")
        @Email
        String email,
        @NotBlank(message = "Uma senha deve ser fornecida")
        String senha,
        //@CPF
        String cpf

) {
}
