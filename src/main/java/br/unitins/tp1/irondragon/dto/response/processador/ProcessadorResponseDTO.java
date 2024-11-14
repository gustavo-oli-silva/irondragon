package br.unitins.tp1.irondragon.dto.response.processador;

import br.unitins.tp1.irondragon.dto.request.processador.ConectividadeRequestDTO;
import br.unitins.tp1.irondragon.dto.request.processador.ConsumoEnergeticoRequestDTO;
import br.unitins.tp1.irondragon.dto.request.processador.FrequenciaRequestDTO;
import br.unitins.tp1.irondragon.dto.request.processador.MemoriaCacheRequestDTO;
import br.unitins.tp1.irondragon.dto.response.FabricanteResponseDTO;
import br.unitins.tp1.irondragon.model.processador.Processador;

import java.util.Optional;

public record ProcessadorResponseDTO(
    Long id,
    String nome,
    String nomeImagem,
    String socket,
    Integer threads,
    Integer nucleos,
    Boolean desbloqueado,
    Double preco,
    Optional<PlacaIntegradaResponseDTO> placaIntegrada,
    FabricanteResponseDTO fabricante,
    MemoriaCacheResponseDTO memoriaCache,
    FrequenciaResponseDTO frequencia,
    ConsumoEnergeticoResponseDTO consumoEnergetico,
    ConectividadeResponseDTO conectividade
) {
        public static ProcessadorResponseDTO valueOf(Processador processador) {
            return new ProcessadorResponseDTO (
                    processador.getId(),
                    processador.getNome(),
                    processador.getNomeImagem(),
                    processador.getSocket(),
                    processador.getThreads(),
                    processador.getNucleos(),
                    processador.getDesbloqueado(),
                    processador.getPreco(),
                    Optional.ofNullable(processador.getPlacaIntegrada())
                            .map(PlacaIntegradaResponseDTO::valueOf),
                    FabricanteResponseDTO.valueOf(processador.getFabricante()),
                    MemoriaCacheResponseDTO.valueOf(processador.getMemoriaCache()),
                    FrequenciaResponseDTO.valueOf(processador.getFrequencia()),
                    ConsumoEnergeticoResponseDTO.valueOf(processador.getConsumoEnergetico()),
                    ConectividadeResponseDTO.valueOf(processador.getConectividade())
            );
        }
}
