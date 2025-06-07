package br.unitins.tp1.irondragon.dto.request.processador;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ImagemProcessadorRequestDTO(
    @NotBlank(message = "O nome da imagem deve ser informado")
    String nomeImagem,
    @NotBlank(message = "A imagem deve ser informada")
    String imagem,
    @NotNull(message = "O campo principal deve ser informado")
    boolean principal,
    int index
) {

}
