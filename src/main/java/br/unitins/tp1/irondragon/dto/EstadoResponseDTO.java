package br.unitins.tp1.irondragon.dto;

import br.unitins.tp1.irondragon.model.Estado;

public record EstadoResponseDTO (
    String nome,
    String sigla
) {
    public static EstadoResponseDTO valueOf(Estado estado) {
        return new EstadoResponseDTO(
            estado.getNome(), 
            estado.getSigla()
        );
    }
}
