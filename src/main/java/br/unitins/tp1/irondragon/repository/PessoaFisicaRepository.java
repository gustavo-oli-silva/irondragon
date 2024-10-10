package br.unitins.tp1.irondragon.repository;

import br.unitins.tp1.irondragon.model.PessoaFisica;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class PessoaFisicaRepository implements PanacheRepository<PessoaFisica> {
    public List<PessoaFisica> findByNome(String nome) {
        return find("SELECT p FROM PessoaFisica p WHERE p.nome LIKE ?1", "%" + nome + "%").list();
    }
}
