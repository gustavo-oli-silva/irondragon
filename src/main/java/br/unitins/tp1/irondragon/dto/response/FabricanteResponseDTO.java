package br.unitins.tp1.irondragon.dto.response;

import br.unitins.tp1.irondragon.model.Fabricante;
import br.unitins.tp1.irondragon.model.TelefoneFabricante;

public record FabricanteResponseDTO(
        Long id,
        String nome,
        String email,
        TelefoneResponseDTO telefone
) {
    public static FabricanteResponseDTO valueOf(Fabricante fabricante) {
        return new FabricanteResponseDTO (
                fabricante.getId(),
                fabricante.getNome(),
                fabricante.getEmail(),
                TelefoneResponseDTO.valueOf(fabricante.getTelefone())
        );
    }
}
