package br.unitins.tp1.irondragon.dto.request;

import br.unitins.tp1.irondragon.model.Endereco;
import br.unitins.tp1.irondragon.model.pedido.EnderecoEntrega;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record EnderecoRequestDTO(
        @NotBlank(message = "logradouro deve ser informado!")
        String logradouro,
        @NotBlank(message = "cep deve ser informado!")
        String cep,
        @NotBlank(message = "bairro deve ser informado!")
        String bairro,
        String complemento,
        @NotNull(message = "Número deve ser informado")
        Integer numero,
        @NotNull(message = "idCidade deve ser informado!")
        Long idCidade
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
