package br.unitins.tp1.irondragon.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record EstadoRequestDTO (
        @NotBlank
        @Size(max = 60)
        String nome,
        @Size(min = 2, max = 2, message = "A sigla deve ter 2 caracteres!")
        String sigla
) {
    
}
