package br.unitins.tp1.irondragon.repository;

import br.unitins.tp1.irondragon.model.Fornecedor;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class FornecedorRepository implements PanacheRepository<Fornecedor> {
    public List<Fornecedor> findByNome(String nome) {
        return find("SELECT f FROM Fornecedor f WHERE f.nome LIKE ?1", "%" + nome + "%").list();
    }
}
