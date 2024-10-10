package br.unitins.tp1.irondragon.dto.response;

import br.unitins.tp1.irondragon.model.Cliente;

import java.util.List;

public record ClienteResponseDTO(
        Long id,
        String nome,
        String email,
        String cpf,
        List<CartaoResponseDTO> cartoes
) {
    public static ClienteResponseDTO valueOf(Cliente cliente) {
        return new ClienteResponseDTO(
                cliente.getId(),
                cliente.getNome(),
                cliente.getEmail(),
                cliente.getCpf(),
                cliente.getCartoes().stream().map(CartaoResponseDTO::valueOf).toList()
        );
    }
}
