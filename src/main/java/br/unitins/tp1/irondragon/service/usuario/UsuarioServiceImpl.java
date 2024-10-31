package br.unitins.tp1.irondragon.service.usuario;

import br.unitins.tp1.irondragon.model.Usuario;
import br.unitins.tp1.irondragon.repository.UsuarioRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;

@ApplicationScoped
public class UsuarioServiceImpl implements UsuarioService {
    @Inject
    public UsuarioRepository usuarioRepository;

    @Override
    public Usuario findById(Long id) {
        return usuarioRepository.findById(id);
    }

    @Override
    public List<Usuario> findByNome(String nome) {
        return List.of();
    }

    @Override
    public Usuario findByUsernameAndSenha(String username, String senha) {
        return usuarioRepository.findByUsernameAndSenha(username, senha);
    }

    @Override
    public List<Usuario> findAll() {
        return usuarioRepository.findAll().list();
    }

    @Override
    public void delete(Long id) {
        usuarioRepository.deleteById(id);
    }
}
