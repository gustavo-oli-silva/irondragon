package br.unitins.tp1.irondragon.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record FabricanteRequestDTO(
    @NotBlank(message = "O nome do fabricante deve ser informado.")
    @Size(min = 2, max = 60, message = "O nome deve ter entre 2 e 60 caracteres.")
    String nome,

    @NotBlank(message = "O e-mail deve ser informado.")
    @Email(message = "O e-mail informado deve ser válido")
    String email,

    TelefoneRequestDTO telefone
) {}
