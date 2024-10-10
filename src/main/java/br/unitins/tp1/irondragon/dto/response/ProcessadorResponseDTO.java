package br.unitins.tp1.irondragon.dto.response;

import br.unitins.tp1.irondragon.model.Processador;

public record ProcessadorResponseDTO(
    Long id,
    String nome,
    String socket,
    Integer threads,
    Integer nucleos,
    Boolean desbloqueado,
    Double preco,
    PlacaIntegradaResponseDTO placaIntegrada,
    FabricanteResponseDTO fabricante
) {
    public static ProcessadorResponseDTO valueOf(Processador processador) {
        return new ProcessadorResponseDTO (
                processador.getId(),
                processador.getNome(),
                processador.getSocket(),
                processador.getThreads(),
                processador.getNucleos(),
                processador.getDesbloqueado(),
                processador.getPreco(),
                PlacaIntegradaResponseDTO.valueOf(processador.getPlacaIntegrada()),
                FabricanteResponseDTO.valueOf(processador.getFabricante())
        );
    }
}
