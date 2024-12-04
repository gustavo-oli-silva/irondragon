package br.unitins.tp1.irondragon.dto.response.processador;

import br.unitins.tp1.irondragon.dto.response.FabricanteResponseDTO;
import br.unitins.tp1.irondragon.model.processador.Processador;

import java.util.List;
import java.util.Optional;

public record ProcessadorResponseDTO(
        Long id,
        String nome,
        List<String> imagens,
        String socket,
        Integer threads,
        Integer nucleos,
        Boolean desbloqueado,
        Double preco,
        Optional<PlacaIntegradaResponseDTO> placaIntegrada,
        MemoriaCacheResponseDTO memoriaCache,
        FrequenciaResponseDTO frequencia,
        ConsumoEnergeticoResponseDTO consumoEnergetico,
        ConectividadeResponseDTO conectividade,
        FabricanteResponseDTO fabricante
) {
        public static ProcessadorResponseDTO valueOf(Processador processador) {
            return new ProcessadorResponseDTO (
                    processador.getId(),
                    processador.getNome(),
                    processador.getImagens(),
                    processador.getSocket(),
                    processador.getThreads(),
                    processador.getNucleos(),
                    processador.getDesbloqueado(),
                    processador.getPreco(),
                    Optional.ofNullable(processador.getPlacaIntegrada())
                            .map(PlacaIntegradaResponseDTO::valueOf),
                    MemoriaCacheResponseDTO.valueOf(processador.getMemoriaCache()),
                    FrequenciaResponseDTO.valueOf(processador.getFrequencia()),
                    ConsumoEnergeticoResponseDTO.valueOf(processador.getConsumoEnergetico()),
                    ConectividadeResponseDTO.valueOf(processador.getConectividade()),
                    FabricanteResponseDTO.valueOf(processador.getFabricante())
            );
        }
}
