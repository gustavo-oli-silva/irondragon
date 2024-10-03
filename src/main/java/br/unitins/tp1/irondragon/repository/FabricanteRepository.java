package br.unitins.tp1.irondragon.repository;

import br.unitins.tp1.irondragon.model.Fabricante;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class FabricanteRepository implements PanacheRepository<Fabricante> {
    public List<Fabricante> findByNome(String nome) {
        return find("SELECT f FROM Fabricante f WHERE f.nome LIKE ?1", "%" + nome + "%").list();
    }
}
