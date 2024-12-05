package br.unitins.tp1.irondragon.service.funcionario;

import br.unitins.tp1.irondragon.dto.request.usuario.CargoUpdateDTO;
import br.unitins.tp1.irondragon.dto.request.usuario.FuncionarioRequestDTO;
import br.unitins.tp1.irondragon.dto.request.usuario.SalarioUpdateDTO;
import br.unitins.tp1.irondragon.model.processador.Processador;
import br.unitins.tp1.irondragon.model.usuario.Cliente;
import br.unitins.tp1.irondragon.model.usuario.Funcionario;
import jakarta.transaction.Transactional;

import java.util.List;

public interface FuncionarioService {
    Funcionario findById(Long id);

    Funcionario findByIdUsuario(Long idUsuario);

    List<Funcionario> findAll();

    Funcionario create(Long idUsuario, FuncionarioRequestDTO dto);

    Funcionario update(Long idUsuario, FuncionarioRequestDTO dto);

    void updateCargo(Long id, CargoUpdateDTO dto);

    void updateSalario(Long id, SalarioUpdateDTO dto);

    Funcionario findByUsername(String username);

    Funcionario findByCpfAndSenha(String cpf, String senha);

    void delete(Long id);
}
