package br.unitins.tp1.irondragon.repository;

import br.unitins.tp1.irondragon.model.usuario.Usuario;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class UsuarioRepository implements PanacheRepository<Usuario> {
    public Usuario findByUsername(String nome) {
        return find("SELECT u FROM Usuario u WHERE u.username = ?1", nome).firstResult();
    }

    public Usuario findByUsernameAndSenha(String username, String senha) {
        return find("SELECT u FROM Usuario u WHERE u.username = ?1 and u.senha = ?2", username, senha).firstResult();
    }

    public Usuario findByEmail(String email) {
        return find("SELECT u FROM Usuario u WHERE u.email = ?1", email).firstResult();
    }
}
