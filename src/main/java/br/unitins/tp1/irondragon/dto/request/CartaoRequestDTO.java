package br.unitins.tp1.irondragon.dto.request;

import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;

public record CartaoRequestDTO(
        String nomeTitular,
        String numero,
        @CPF
        String cpf,
        LocalDate validade,
        Integer cvc
) {
}
