package br.unitins.tp1.irondragon.service.funcionario;

import br.unitins.tp1.irondragon.model.usuario.Funcionario;
import br.unitins.tp1.irondragon.repository.FuncionarioRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;

@ApplicationScoped
public class FuncionarioServiceImpl implements FuncionarioService {
    @Inject
    public FuncionarioRepository funcionarioRepository;

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

    @Override
    public Funcionario create(String username) {
        return null;
    }

    @Override
    public Funcionario findByUsername(String username) {
        return funcionarioRepository.findFuncionarioByUsername(username);
    }

    @Override
    public void delete(Long id) {

    }
}
