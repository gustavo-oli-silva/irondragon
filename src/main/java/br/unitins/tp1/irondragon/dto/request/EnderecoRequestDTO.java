package br.unitins.tp1.irondragon.dto.request;

import br.unitins.tp1.irondragon.model.Endereco;
import br.unitins.tp1.irondragon.model.pedido.EnderecoEntrega;

public record EnderecoRequestDTO(
        String logradouro,
        String cep,
        String bairro,
        String complemento,
        Integer numero
) {
    public Endereco toEntityEndereco() {
        Endereco endereco = new Endereco();
        endereco.setLogradouro(logradouro());
        endereco.setCep(cep());
        endereco.setBairro(bairro());
        endereco.setComplemento(complemento());
        endereco.setNumero(numero());

        return endereco;
    }

    public EnderecoEntrega toEntityEnderecoEntrega() {
        EnderecoEntrega endereco = new EnderecoEntrega();
        endereco.setLogradouro(logradouro());
        endereco.setCep(cep());
        endereco.setBairro(bairro());
        endereco.setComplemento(complemento());
        endereco.setNumero(numero());

        return endereco;
    }
}
