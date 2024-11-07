package br.unitins.tp1.irondragon.repository;

import java.util.List;

import br.unitins.tp1.irondragon.model.processador.Processador;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ProcessadorRepository implements PanacheRepository<Processador> {
    public List<Processador> findByNome(String nome) {
        return find("SELECT p FROM Processador p WHERE p.nome like ?1", "%" + nome + "%").list();
    }
}
