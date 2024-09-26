package br.unitins.tp1.irondragon.dto;

public record ProcessadorRequestDTO (
    String nome,
    String socket,
    Integer threads,
    Integer nucleos,
    Boolean desbloqueado,
    Double preco
) {
    
}
