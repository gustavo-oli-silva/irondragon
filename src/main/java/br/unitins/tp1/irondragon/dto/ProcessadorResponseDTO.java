package br.unitins.tp1.irondragon.dto;

import br.unitins.tp1.irondragon.model.Processador;

public record ProcessadorResponseDTO(
    Long id,
    String nome,
    String socket,
    Integer threads,
    Integer nucleos,
    Boolean desbloqueado,
    Double preco
) {
    public static ProcessadorResponseDTO valueOf(Processador processador) {
        return new ProcessadorResponseDTO (
            processador.getId(),
            processador.getNome(),
            processador.getSocket(),
            processador.getThreads(), 
            processador.getNucleos(),
            processador.getDesbloqueado(), 
            processador.getPreco()
        );
    }
}
