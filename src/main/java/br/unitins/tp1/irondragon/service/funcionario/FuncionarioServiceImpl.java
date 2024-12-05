package br.unitins.tp1.irondragon.service.funcionario;

import br.unitins.tp1.irondragon.dto.request.usuario.CargoUpdateDTO;
import br.unitins.tp1.irondragon.dto.request.usuario.FuncionarioRequestDTO;
import br.unitins.tp1.irondragon.dto.request.usuario.SalarioUpdateDTO;
import br.unitins.tp1.irondragon.model.usuario.Funcionario;
import br.unitins.tp1.irondragon.model.usuario.Usuario;
import br.unitins.tp1.irondragon.repository.FuncionarioRepository;
import br.unitins.tp1.irondragon.service.usuario.UsuarioService;
import br.unitins.tp1.irondragon.validation.ValidationException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
public class FuncionarioServiceImpl implements FuncionarioService {
    @Inject
    public FuncionarioRepository funcionarioRepository;

    @Inject
    public UsuarioService usuarioService;

    @Override
    public Funcionario findById(Long id) {
        return funcionarioRepository.findById(id);
    }

    @Override
    public Funcionario findByIdUsuario(Long idUsuario) {
        return null;
    }

    @Override
    public List<Funcionario> findAll() {
        return funcionarioRepository.listAll();
    }

    @Transactional
    @Override
    public Funcionario create(Long idUsuario, FuncionarioRequestDTO dto) {
        Usuario usuario = usuarioService.findById(idUsuario);

        if(usuario == null) throw new ValidationException("usuario", "Usuário não existe!");

        Funcionario verificacao = funcionarioRepository.findFuncionarioByUsername(usuario.getUsername());

        if(verificacao != null) throw new ValidationException("id", "Funcionário já existente!");

        Funcionario funcionario = new Funcionario();
        funcionario.setDataContratacao(dto.dataContratacao());
        funcionario.setCargo(dto.cargo());
        funcionario.setUsuario(usuario);
        funcionario.setSalario(dto.salario());

        funcionarioRepository.persist(funcionario);

        return funcionario;
    }

    @Transactional
    @Override
    public Funcionario update(Long id, FuncionarioRequestDTO dto) {
        Funcionario funcionario = funcionarioRepository.findById(id);

        if(funcionario == null) throw new ValidationException("id", "Funcionário inexistente!");

        funcionario.setDataContratacao(dto.dataContratacao());
        funcionario.setCargo(dto.cargo());
        funcionario.setSalario(dto.salario());

        return funcionario;
    }

    @Transactional
    @Override
    public void updateCargo(Long id, CargoUpdateDTO dto) {
        Funcionario funcionario = funcionarioRepository.findById(id);

        if(funcionario == null) {
            throw new ValidationException("id", "Funcionário inexistente!");
        }

        funcionario.setCargo(dto.cargo());
    }

    @Transactional
    @Override
    public void updateSalario(Long id, SalarioUpdateDTO dto) {
        Funcionario funcionario = funcionarioRepository.findById(id);

        if(funcionario == null) {
            throw new ValidationException("id", "Funcionário inexistente!");
        }

        funcionario.setSalario(dto.salario());
    }

    @Override
    public Funcionario findByUsername(String username) {
        return funcionarioRepository.findFuncionarioByUsername(username);
    }

    @Override
    public Funcionario findByCpfAndSenha(String cpf, String senha) {
        return funcionarioRepository.findFuncionarioByCpfAndSenha(cpf, senha);
    }

    @Transactional
    @Override
    public void delete(Long id) {
        Funcionario funcionario = funcionarioRepository.findById(id);

        if(funcionario == null) {
            throw new ValidationException("id", "Funcionário inexistente!");
        }

        funcionarioRepository.deleteById(id);
    }
}
