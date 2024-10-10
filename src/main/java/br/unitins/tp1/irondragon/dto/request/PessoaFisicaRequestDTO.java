package br.unitins.tp1.irondragon.dto.request;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.br.CPF;

public record PessoaFisicaRequestDTO(
        @NotBlank(message = "O nome não pode ser nulo!")
        String nome,
        @CPF(message = "Um cpf válido deve ser informado!")
        String cpf,
        Integer idSexo
) {

}

