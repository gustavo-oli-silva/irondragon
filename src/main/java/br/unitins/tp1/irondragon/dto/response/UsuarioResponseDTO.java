package br.unitins.tp1.irondragon.dto.response;

import br.unitins.tp1.irondragon.model.Perfil;
import br.unitins.tp1.irondragon.model.usuario.Usuario;

import java.time.LocalDateTime;

public record UsuarioResponseDTO(
        Long id,
        String username,
        String senha,
        LocalDateTime dataCriacao,
        Perfil perfil
) {
    public static UsuarioResponseDTO valueOf(Usuario usuario) {
        return new UsuarioResponseDTO(
                usuario.getId(),
                usuario.getUsername(),
                usuario.getSenha(),
                usuario.getDataCriacao(),
                usuario.getPerfil()
        );
    }
}
