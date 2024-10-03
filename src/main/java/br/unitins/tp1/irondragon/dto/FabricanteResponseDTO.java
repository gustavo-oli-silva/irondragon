package br.unitins.tp1.irondragon.dto;

import br.unitins.tp1.irondragon.model.Fabricante;

public record FabricanteResponseDTO(
        Long id,
        String nome,
        String email
) {
    public static FabricanteResponseDTO valueOf(Fabricante fabricante) {
        return new FabricanteResponseDTO(
                fabricante.getId(),
                fabricante.getNome(),
                fabricante.getEmail()
        );
    }
}
