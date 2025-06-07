package br.unitins.tp1.irondragon.dto.response.processador;

import br.unitins.tp1.irondragon.model.ImagemProcessador;

public record ImagemProcessadorResponseDTO(
        Long id,
        String nomeImagem,
        String imagem,
        boolean principal,
        int index
) {

    public static ImagemProcessadorResponseDTO valueOf(ImagemProcessador imagemProcessador) {
        return new ImagemProcessadorResponseDTO(imagemProcessador.getId(), imagemProcessador.getNomeImagem(), imagemProcessador.getImagem(), imagemProcessador.isPrincipal(), imagemProcessador.getIndex());
    }
}
