package br.unitins.tp1.irondragon.dto.response;

import br.unitins.tp1.irondragon.model.Endereco;
import br.unitins.tp1.irondragon.model.pedido.EnderecoEntrega;

public record EnderecoResponseDTO(
        String logradouro,
        String cep,
        String bairro,
        String complemento,
        Integer numero
) {
    public static EnderecoResponseDTO valueOf(Endereco endereco) {
        return new EnderecoResponseDTO(
                endereco.getLogradouro(),
                endereco.getCep(),
                endereco.getBairro(),
                endereco.getComplemento(),
                endereco.getNumero()
        );
    }

    public static EnderecoResponseDTO valueOf(EnderecoEntrega endereco) {
        return new EnderecoResponseDTO(
                endereco.getLogradouro(),
                endereco.getCep(),
                endereco.getBairro(),
                endereco.getComplemento(),
                endereco.getNumero()
        );
    }
}
