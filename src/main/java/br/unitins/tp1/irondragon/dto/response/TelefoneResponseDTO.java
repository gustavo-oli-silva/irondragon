package br.unitins.tp1.irondragon.dto.response;

import br.unitins.tp1.irondragon.model.TelefoneFabricante;
import br.unitins.tp1.irondragon.model.TelefoneFornecedor;

public record TelefoneResponseDTO(
        Long id,
        String codigoArea,
        String numero
) {
    public static TelefoneResponseDTO valueOf(TelefoneFornecedor telefoneFornecedor) {
        return new TelefoneResponseDTO(
                telefoneFornecedor.getId(),
                telefoneFornecedor.getCodigoArea(),
                telefoneFornecedor.getNumero()
        );
    }

    public static TelefoneResponseDTO valueOf(TelefoneFabricante telefoneFabricante) {
        return new TelefoneResponseDTO(
                telefoneFabricante.getId(),
                telefoneFabricante.getCodigoArea(),
                telefoneFabricante.getNumero()
        );
    }
}
