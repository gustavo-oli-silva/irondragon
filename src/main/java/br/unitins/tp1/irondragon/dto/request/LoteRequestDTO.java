package br.unitins.tp1.irondragon.dto.request;

import java.time.LocalDate;

public record LoteRequestDTO (
        Long idProcessador,
        LocalDate data,
        String codigo,
        Integer estoque
) {

}
