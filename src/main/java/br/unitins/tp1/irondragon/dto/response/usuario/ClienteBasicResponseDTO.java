package br.unitins.tp1.irondragon.dto.response.usuario;

import br.unitins.tp1.irondragon.model.usuario.Cliente;

public record ClienteBasicResponseDTO(
        Long id, String nome, String email) {

    public static ClienteBasicResponseDTO valueOf(Cliente cliente) {
        return new ClienteBasicResponseDTO(
                cliente.getId(),
                cliente.getUsuario().getNome(),
                cliente.getUsuario().getEmail());
    }
}
