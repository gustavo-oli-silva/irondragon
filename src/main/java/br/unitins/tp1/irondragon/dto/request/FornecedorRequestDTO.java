package br.unitins.tp1.irondragon.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record FornecedorRequestDTO(
        @NotBlank(message = "Nome do fornecedor não pode ser nulo!")
        String nome,
        @Email
        String email,
        TelefoneRequestDTO telefone
) {
}
