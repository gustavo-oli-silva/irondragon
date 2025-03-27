package br.unitins.tp1.irondragon.service.fornecedor;

import br.unitins.tp1.irondragon.dto.request.FornecedorRequestDTO;
import br.unitins.tp1.irondragon.model.Fornecedor;
import br.unitins.tp1.irondragon.repository.FornecedorRepository;
import br.unitins.tp1.irondragon.validation.ValidationException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
public class FornecedorServiceImpl implements FornecedorService {
    @Inject
    public FornecedorRepository fornecedorRepository;

    @Override
    public Fornecedor findById(Long id) {
        Fornecedor fornecedor = fornecedorRepository.findById(id);

        if(fornecedor == null) {
            throw new ValidationException("id", "Fornecedor informado não existe!");
        }

        return fornecedor;
    }

    @Override
    public List<Fornecedor> findByNome(String nome, Integer page, Integer pageSize) {
        return fornecedorRepository
                .findByNome(nome)
                .page(page, pageSize)
                .list();
    }

    @Override
    public List<Fornecedor> findAll() {
        return fornecedorRepository.findAll().list();
    }

    @Override
    public List<Fornecedor> findAll(Integer page, Integer pageSize) {
        return fornecedorRepository.findAll().page(page, pageSize).list();
    }

    @Transactional
    @Override
    public Fornecedor create(FornecedorRequestDTO dto) {
        Fornecedor fornecedor = new Fornecedor();
        fornecedor.setNome(dto.nome());
        fornecedor.setEmail(dto.email());
        fornecedor.setTelefone(dto.telefone().toEntityTelefoneFornecedor());

        fornecedorRepository.persist(fornecedor);

        return fornecedor;
    }

    @Transactional
    @Override
    public void update(Long id, FornecedorRequestDTO dto) {
        Fornecedor fornecedor = fornecedorRepository.findById(id);

        if(fornecedor == null) {
            throw new ValidationException("id", "Fornecedor informado não existe!");
        }

        fornecedor.setNome(dto.nome());
        fornecedor.setEmail(dto.email());
        fornecedor.setTelefone(dto.telefone().toEntityTelefoneFornecedor());
    }

    @Transactional
    @Override
    public void delete(Long id) {
        Fornecedor fornecedor = fornecedorRepository.findById(id);

        if(fornecedor == null) {
            throw new ValidationException("id", "Fornecedor informado não existe!");
        }

        fornecedorRepository.deleteById(id);
    }

    @Override
    public Long count() {
        return fornecedorRepository.count();
    }

    @Override
    public Long count(String nome) {
        return fornecedorRepository.findByNome(nome).count();
    }
}
