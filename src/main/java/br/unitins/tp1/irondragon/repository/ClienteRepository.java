package br.unitins.tp1.irondragon.repository;

import br.unitins.tp1.irondragon.model.Cliente;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class ClienteRepository implements PanacheRepository<Cliente> {
    public List<Cliente> findByNome(String nome) {
        return find("SELECT c FROM Cliente c WHERE c.nome LIKE ?1", "%" + nome + "%").list();
    }
}
