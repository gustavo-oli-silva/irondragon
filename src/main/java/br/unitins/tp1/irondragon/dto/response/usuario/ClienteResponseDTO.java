package br.unitins.tp1.irondragon.dto.response.usuario;

import br.unitins.tp1.irondragon.dto.response.CartaoResponseDTO;
import br.unitins.tp1.irondragon.model.usuario.Cliente;

import java.util.List;

public record ClienteResponseDTO(
        Long id,
        UsuarioResponseDTO usuario,
        List<CartaoResponseDTO> cartoes
) {
    public static ClienteResponseDTO valueOf(Cliente cliente) {
        return new ClienteResponseDTO(
                cliente.getId(),
                UsuarioResponseDTO.valueOf(cliente.getUsuario()),
                cliente.getListaDeCartoes().stream().map(CartaoResponseDTO::valueOf).toList()
        );
    }
}
