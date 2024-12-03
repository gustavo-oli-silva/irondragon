package br.unitins.tp1.irondragon.repository;

import br.unitins.tp1.irondragon.model.usuario.Funcionario;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class FuncionarioRepository implements PanacheRepository<Funcionario> {
    public Funcionario findFuncionarioByUsername(String username) {
        return find("SELECT f FROM Funcionario f WHERE f.usuario.username = ?1", username).firstResult();
    }

    public Funcionario findFuncionarioByCpfAndSenha(String cpf, String senha) {
        return find("SELECT f FROM Funcionario f WHERE f.usuario.cpf = ?1 AND f.usuario.senha = ?2", cpf, senha).firstResult();
    }
}
