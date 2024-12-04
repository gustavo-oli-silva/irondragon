package br.unitins.tp1.irondragon.dto.response.usuario;

import br.unitins.tp1.irondragon.dto.response.TelefoneResponseDTO;
import br.unitins.tp1.irondragon.model.usuario.Perfil;
import br.unitins.tp1.irondragon.model.usuario.Usuario;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record UsuarioResponseDTO(
        Long id,
        String username,
        String email,
        String senha,
        String cpf,
        LocalDateTime dataCriacao,
        LocalDate dataNascimento,
        TelefoneResponseDTO telefone,
        Perfil perfil,
        String nomeImagem
) {
    public static UsuarioResponseDTO valueOf(Usuario usuario) {
        return new UsuarioResponseDTO(
                usuario.getId(),
                usuario.getUsername(),
                usuario.getEmail(),
                usuario.getSenha(),
                usuario.getCpf(),
                usuario.getDataCriacao(),
                usuario.getDataNascimento(),
                TelefoneResponseDTO.valueOf(usuario.getTelefone()),
                usuario.getPerfil(),
                usuario.getNomeImagem()
        );
    }
}
