package br.unitins.tp1.irondragon.repository;

import br.unitins.tp1.irondragon.model.Usuario;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class UsuarioRepository implements PanacheRepository<Usuario> {
    public Usuario findByUsername(String nome) {
        return find("SELECT u FROM Usuario u WHERE u.username LIKE ?1", "%" + nome + "%").firstResult();
    }

    public Usuario findByUsernameAndSenha(String username, String senha) {
        return find("SELECT u FROM Usuario u WHERE u.username = ?1 and u.senha = ?2", username, senha).firstResult();
    }
}
