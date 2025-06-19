package br.unitins.tp1.irondragon.dto.request.usuario;

import java.time.LocalDate;

import br.unitins.tp1.irondragon.dto.request.TelefoneRequestDTO;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UsuarioUpdateBasicoDTO(
      @NotBlank(message = "O nome  deve ser informado")
        String nome,
        LocalDate dataNascimento,
        TelefoneRequestDTO telefone
) {

}
