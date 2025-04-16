package br.unitins.tp1.irondragon.service.usuario;

import java.util.List;

import br.unitins.tp1.irondragon.dto.request.usuario.EmailUpdateDTO;
import br.unitins.tp1.irondragon.dto.request.usuario.SenhaUpdateDTO;
import br.unitins.tp1.irondragon.dto.request.usuario.UsuarioRequestDTO;
import br.unitins.tp1.irondragon.model.usuario.Perfil;
import br.unitins.tp1.irondragon.model.usuario.Usuario;
import jakarta.transaction.Transactional;

public interface UsuarioService {
    Usuario findById(Long id);

    Usuario findByUsername(String username);

    Usuario findByCpf(String cpf);

    Usuario findByUsernameAndSenha(String username, String senha);

    List<Usuario> findAll();

    Usuario create(UsuarioRequestDTO dto);

    @Transactional
    void updateEmail(EmailUpdateDTO dto, String username);

    @Transactional
    void updateSenha(SenhaUpdateDTO dto, String username);

    @Transactional
    void changeProfile(Usuario usuario, Perfil perfil);

    @Transactional
    Usuario updateNomeImagem(String username, String nomeImagem);

    void delete(Long id);
}
