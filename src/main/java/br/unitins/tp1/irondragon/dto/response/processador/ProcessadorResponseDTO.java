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
        PlacaIntegradaResponseDTO placaIntegradaDTO;

        if (processador.getPlacaIntegrada() == null) {
            placaIntegradaDTO = new PlacaIntegradaResponseDTO(null, null, null, null, null);
        } else {
            placaIntegradaDTO = PlacaIntegradaResponseDTO.valueOf(processador.getPlacaIntegrada());
        }

        return new ProcessadorResponseDTO (
                processador.getId(),
                processador.getNome(),
                processador.getSocket(),
                processador.getThreads(),
                processador.getNucleos(),
                processador.getDesbloqueado(),
                processador.getPreco(),
                Optional.of(PlacaIntegradaResponseDTO.valueOf(processador.getPlacaIntegrada())),
                FabricanteResponseDTO.valueOf(processador.getFabricante()),
                MemoriaCacheResponseDTO.valueOf(processador.getMemoriaCache()),
                FrequenciaResponseDTO.valueOf(processador.getFrequencia()),
                ConsumoEnergeticoResponseDTO.valueOf(processador.getConsumoEnergetico()),
                ConectividadeResponseDTO.valueOf(processador.getConectividade())
        );
    }
}
