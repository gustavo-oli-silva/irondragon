package br.unitins.tp1.irondragon.dto.request;

import br.unitins.tp1.irondragon.model.TelefoneFabricante;
import br.unitins.tp1.irondragon.model.TelefoneFornecedor;
import br.unitins.tp1.irondragon.model.usuario.TelefoneUsuario;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record TelefoneRequestDTO(
        @NotBlank(message = "O código de área não pode ser nulo!")
        @Size(min = 2, max = 3, message = "O código de área deve ter 2 dígitos!")
        String codigoArea,
        @Size(min = 8, max = 9, message = "O número deve ter entre 8 e 9 dígitos!")
        @NotBlank(message = "O número não pode ser nulo!")
        String numero
) {
    public TelefoneFornecedor toEntityTelefoneFornecedor() {
        TelefoneFornecedor telefoneFornecedor = new TelefoneFornecedor();
        telefoneFornecedor.setCodigoArea(codigoArea());
        telefoneFornecedor.setNumero(numero());

        return telefoneFornecedor;
    }

    public TelefoneFabricante toEntityTelefoneFabricante() {
        TelefoneFabricante telefoneFabricante = new TelefoneFabricante();
        telefoneFabricante.setCodigoArea(codigoArea());
        telefoneFabricante.setNumero(numero());

        return telefoneFabricante;
    }

    public TelefoneUsuario toEntityTelefoneUsuario() {
        TelefoneUsuario telefoneUsuario = new TelefoneUsuario();
        telefoneUsuario.setNumero(numero());
        telefoneUsuario.setCodigoArea(codigoArea());

        return telefoneUsuario;
    }
}
