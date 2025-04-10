package br.unitins.tp1.irondragon.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.PositiveOrZero;

import java.time.LocalDate;

public record LoteRequestDTO (

        Long idProcessador,
        @NotNull(message = "A data não pode ser nulo")
        @PastOrPresent(message = "A data do lote não pode estar no futuro")
        LocalDate data,
        @NotBlank(message = "O código deve ser informado")
        String codigo,
        @NotNull(message = "O estoque não pode ser nulo")
        @PositiveOrZero(message = "O estoque não pode ser negativo")
        Integer estoque,
        @NotNull(message = "O idFornecedor não pode ser nulo")
        Long idFornecedor
) {

}
