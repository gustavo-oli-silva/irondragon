package br.unitins.tp1.irondragon.service.funcionario;

import br.unitins.tp1.irondragon.model.processador.Processador;
import br.unitins.tp1.irondragon.model.usuario.Cliente;
import br.unitins.tp1.irondragon.model.usuario.Funcionario;

import java.util.List;

public interface FuncionarioService {
    Funcionario findById(Long id);

    Funcionario findByIdUsuario(Long idUsuario);

    List<Funcionario> findAll();

    Funcionario create(String username);

    Funcionario findByUsername(String username);

    void delete(Long id);
}
