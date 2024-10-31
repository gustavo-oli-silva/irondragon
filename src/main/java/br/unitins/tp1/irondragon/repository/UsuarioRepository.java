package br.unitins.tp1.irondragon.repository;

import br.unitins.tp1.irondragon.model.Usuario;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class UsuarioRepository implements PanacheRepository<Usuario> {
    public List<Usuario> findByUsername(String nome) {
        return find("SELECT u FROM Usuario u WHERE u.username LIKE ?1", "%" + nome + "%").list();
    }
}
