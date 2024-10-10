package br.unitins.tp1.irondragon.dto.request;

import jakarta.validation.constraints.Email;
import org.hibernate.validator.constraints.br.CPF;

public record ClienteRequestDTO(
        String nome,
        @Email
        String email,
        @CPF
        String cpf,
        String senha
) {
}
