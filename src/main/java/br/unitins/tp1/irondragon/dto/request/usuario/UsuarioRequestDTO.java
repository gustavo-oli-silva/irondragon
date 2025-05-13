package br.unitins.tp1.irondragon.dto.request.usuario;

import java.time.LocalDate;

import org.hibernate.validator.constraints.br.CPF;

import br.unitins.tp1.irondragon.dto.request.TelefoneRequestDTO;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UsuarioRequestDTO(
        @NotBlank(message = "O nome  deve ser informado")
        String nome,
        @NotBlank(message = "O email deve ser informado")
        @Email
        String email,
        @NotBlank(message = "Uma senha deve ser fornecida")
        String senha,
        //@CPF
        String cpf,
        @NotNull(message = "dataNascimento deve ser informada!")
        LocalDate dataNascimento,
        TelefoneRequestDTO telefone

) {
}
