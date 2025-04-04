package br.unitins.tp1.irondragon.repository;

import br.unitins.tp1.irondragon.model.processador.PlacaIntegrada;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class PlacaIntegradaRepository implements PanacheRepository<PlacaIntegrada> {
    public PanacheQuery<PlacaIntegrada> findByNome(String nome) {
        return find("SELECT p FROM PlacaIntegrada p WHERE p.nome LIKE ?1", "%" + nome + "%");
    }
}
