package br.unitins.tp1.irondragon.service.fornecedor;

import br.unitins.tp1.irondragon.dto.request.FornecedorRequestDTO;
import br.unitins.tp1.irondragon.model.Fornecedor;

import java.util.List;

public interface FornecedorService {
    public Fornecedor findById(Long id);

    List<Fornecedor> findByNome(String nome, Integer page, Integer pageSize);

    List<Fornecedor> findAll();

    List<Fornecedor> findAll(Integer page, Integer pageSize);

    public Fornecedor create(FornecedorRequestDTO fornecedor);

    public void update(Long id, FornecedorRequestDTO fornecedor);

    public void delete(Long id);

    Long count();

    Long count(String nome);
}
