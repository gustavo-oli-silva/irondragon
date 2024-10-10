package br.unitins.tp1.irondragon.dto.response;

import br.unitins.tp1.irondragon.model.PessoaFisica;
import br.unitins.tp1.irondragon.model.Sexo;

public record PessoaFisicaResponseDTO(
        Long id,
        String nome,
        String cpf,
        Sexo sexo
) {
    public static PessoaFisicaResponseDTO valueOf(PessoaFisica pessoaFisica) {
        return new PessoaFisicaResponseDTO(
                pessoaFisica.getId(),
                pessoaFisica.getNome(),
                pessoaFisica.getCpf(),
                pessoaFisica.getSexo()
        );
    }
}
