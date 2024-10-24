package br.unitins.tp1.irondragon.dto.response;

import br.unitins.tp1.irondragon.model.Usuario;

import java.util.List;

public record ClienteResponseDTO(
        Long id,
        String nome,
        String email,
        String cpf,
        List<CartaoResponseDTO> cartoes
) {
    public static ClienteResponseDTO valueOf(Usuario usuario) {
        return new ClienteResponseDTO(
                usuario.getId(),
                usuario.getNome(),
                usuario.getEmail(),
                usuario.getCpf(),
                usuario.getCartoes().stream().map(CartaoResponseDTO::valueOf).toList()
        );
    }
}
