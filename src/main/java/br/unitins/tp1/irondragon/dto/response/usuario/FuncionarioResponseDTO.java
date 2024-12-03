package br.unitins.tp1.irondragon.dto.response.usuario;

import br.unitins.tp1.irondragon.model.usuario.Funcionario;
import br.unitins.tp1.irondragon.model.usuario.Usuario;

import java.time.LocalDate;

public record FuncionarioResponseDTO(
        Long id,
        UsuarioResponseDTO usuario,
        LocalDate dataContratacao,
        String cargo,
        Double salario
) {
    public static FuncionarioResponseDTO valueOf(Funcionario funcionario) {
        return new FuncionarioResponseDTO(
                funcionario.getId(),
                UsuarioResponseDTO.valueOf(funcionario.getUsuario()),
                funcionario.getDataContratacao(),
                funcionario.getCargo(),
                funcionario.getSalario()
        );
    }
}