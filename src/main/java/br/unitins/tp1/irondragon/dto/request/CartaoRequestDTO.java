package br.unitins.tp1.irondragon.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;

public record CartaoRequestDTO(
        @NotBlank(message = "nomeTitular não pode ser nulo")
        String nomeTitular,
        @NotBlank(message = "numero não pode ser nulo")
        String numero,
        @CPF
        String cpf,
        @NotNull(message = "Validade deve ser informada!")
        LocalDate validade,
        @NotNull(message = "cvc deve ser informado!")
        Integer cvc,
        @NotNull(message = "Tipo cartão deve ser informado!")
        Integer tipoCartao
) {
}
