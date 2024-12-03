package br.unitins.tp1.irondragon.service.usuario;

import br.unitins.tp1.irondragon.dto.request.usuario.UsuarioRequestDTO;
import br.unitins.tp1.irondragon.model.usuario.Perfil;
import br.unitins.tp1.irondragon.model.usuario.Usuario;
import jakarta.transaction.Transactional;

import java.util.List;

public interface UsuarioService {
    Usuario findById(Long id);

    Usuario findByUsername(String username);

    Usuario findByUsernameAndSenha(String username, String senha);

    List<Usuario> findAll();

    Usuario create(UsuarioRequestDTO dto);

//    public void update(Long id, EstadoRequestDTO dto);

    @Transactional
    void changeProfile(Usuario usuario, Perfil perfil);

    void delete(Long id);
}
