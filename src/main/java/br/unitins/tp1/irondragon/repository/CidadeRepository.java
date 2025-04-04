package br.unitins.tp1.irondragon.repository;

import java.util.List;

import br.unitins.tp1.irondragon.model.Cidade;
import br.unitins.tp1.irondragon.model.Estado;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CidadeRepository implements PanacheRepository<Cidade> {
    public PanacheQuery<Cidade> findByNome(String nome) {
        return find("SELECT c FROM Cidade c where c.nome LIKE ?1", "%" + nome + "%");
    }

    public List<Cidade> findByEstado(Estado estado) {
        return find("SELECT c FROM Cidade c WHERE c.estado.id = ?1", estado.getId()).list();
    }
}
