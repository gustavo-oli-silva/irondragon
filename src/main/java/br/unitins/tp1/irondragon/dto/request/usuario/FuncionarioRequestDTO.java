package br.unitins.tp1.irondragon.dto.request.usuario;

import br.unitins.tp1.irondragon.model.usuario.Usuario;

import java.time.LocalDate;

public record FuncionarioRequestDTO(
        LocalDate dataContratacao,
        String cargo,
        Double salario
) {
}
