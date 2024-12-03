package br.unitins.tp1.irondragon.service.funcionario;

import br.unitins.tp1.irondragon.dto.request.usuario.FuncionarioRequestDTO;
import br.unitins.tp1.irondragon.model.processador.Processador;
import br.unitins.tp1.irondragon.model.usuario.Cliente;
import br.unitins.tp1.irondragon.model.usuario.Funcionario;

import java.util.List;

public interface FuncionarioService {
    Funcionario findById(Long id);

    Funcionario findByIdUsuario(Long idUsuario);

    List<Funcionario> findAll();

    Funcionario create(Long idUsuario, FuncionarioRequestDTO dto);

    Funcionario findByUsername(String username);

    Funcionario findByCpfAndSenha(String cpf, String senha);

    void delete(Long id);
}
