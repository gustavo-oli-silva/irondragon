package br.unitins.tp1.irondragon.service;

import br.unitins.tp1.irondragon.dto.request.FornecedorRequestDTO;
import br.unitins.tp1.irondragon.model.Fornecedor;

import java.util.List;

public interface FornecedorService {
    public Fornecedor findById(Long id);

    public List<Fornecedor> findByNome(String nome);

    public List<Fornecedor> findAll();

    public Fornecedor create(FornecedorRequestDTO cidade);

    public void update(Long id, FornecedorRequestDTO cidade);

    public void delete(Long id);
}
