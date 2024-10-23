package br.unitins.tp1.irondragon.repository;

import br.unitins.tp1.irondragon.model.Cartao;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class CartaoRepository implements PanacheRepository<Cartao> {
    public List<Cartao> findByNome(String nome) {
        return find("SELECT c FROM Cartao c WHERE c.nomeTitular LIKE ?1", "%" + nome + "%").list();
    }
}
