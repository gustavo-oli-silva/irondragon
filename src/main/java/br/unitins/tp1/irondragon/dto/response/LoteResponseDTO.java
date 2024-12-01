package br.unitins.tp1.irondragon.dto.response;

import br.unitins.tp1.irondragon.dto.response.processador.ProcessadorResponseDTO;
import br.unitins.tp1.irondragon.model.pedido.Lote;

import java.time.LocalDate;

public record LoteResponseDTO(
        Long id,
        FornecedorResponseDTO fornecedor,
        LocalDate data,
        String codigo,
        Integer estoque,
        ProcessadorResponseDTO processador
) {
    public static LoteResponseDTO valueOf(Lote lote) {
        return new LoteResponseDTO(
                lote.getId(),
                FornecedorResponseDTO.valueOf(lote.getFornecedor()),
                lote.getData(),
                lote.getCodigo(),
                lote.getEstoque(),
                ProcessadorResponseDTO.valueOf(lote.getProcessador())
        );
    }
}
