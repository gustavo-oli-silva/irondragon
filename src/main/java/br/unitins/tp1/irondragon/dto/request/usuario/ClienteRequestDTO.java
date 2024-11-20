package br.unitins.tp1.irondragon.dto.request.usuario;

import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;

public record ClienteRequestDTO(
        @CPF
        String cpf
) {
}
