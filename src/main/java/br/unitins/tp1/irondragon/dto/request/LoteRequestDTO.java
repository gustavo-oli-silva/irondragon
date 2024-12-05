package br.unitins.tp1.irondragon.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record LoteRequestDTO (
        Long idProcessador,
        @NotNull(message = "data não pode ser nulo")
        LocalDate data,
        @NotBlank(message = "O código deve ser informado")
        String codigo,
        @NotNull(message = "estoque não pode ser nulo")
        Integer estoque,
        @NotNull(message = "idFornecedor não pode ser nulo")
        Long idFornecedor
) {

}
