package br.unitins.tp1.irondragon.repository;

import br.unitins.tp1.irondragon.model.usuario.Funcionario;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class FuncionarioRepository implements PanacheRepository<Funcionario> {
    public Funcionario findFuncionarioByUsername(String username) {
        return find("SELECT f FROM Funcionario f WHERE f.usuario.username = ?1", username).firstResult();
    }
}
