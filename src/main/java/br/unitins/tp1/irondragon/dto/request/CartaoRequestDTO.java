package br.unitins.tp1.irondragon.dto.request;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CartaoRequestDTO(
        @NotBlank(message = "nomeTitular não pode ser nulo")
        String nomeTitular,
        @NotBlank(message = "numero não pode ser nulo")
        String numero,
        String cpf,
        @NotNull(message = "Validade deve ser informada!")
        LocalDate validade,
        @NotNull(message = "cvc deve ser informado!")
        Integer cvc,
        @NotNull(message = "Tipo cartão deve ser informado!")
        Integer tipoCartao
) {
}
