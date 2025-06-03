package br.unitins.tp1.irondragon.dto.response.usuario;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import br.unitins.tp1.irondragon.dto.response.EnderecoResponseDTO;
import br.unitins.tp1.irondragon.dto.response.TelefoneResponseDTO;
import br.unitins.tp1.irondragon.model.usuario.Perfil;
import br.unitins.tp1.irondragon.model.usuario.Usuario;

public record UsuarioResponseDTO(
        Long id,
        String nome,
        String email,
        String senha,
        String cpf,
        LocalDateTime dataCriacao,
        LocalDate dataNascimento,
        TelefoneResponseDTO telefone,
        List<EnderecoResponseDTO> enderecos,
        Perfil perfil,
        String nomeImagem
) {
    public static UsuarioResponseDTO valueOf(Usuario usuario) {
        return new UsuarioResponseDTO(
                usuario.getId(),
                usuario.getNome(),
                usuario.getEmail(),
                usuario.getSenha(),
                usuario.getCpf(),
                usuario.getDataCriacao(),
                usuario.getDataNascimento(),
                TelefoneResponseDTO.valueOf(usuario.getTelefone()),
                usuario.getEnderecos().stream().map(EnderecoResponseDTO::valueOf).toList(),
                usuario.getPerfil(),
                usuario.getNomeImagem()
        );
    }
}
