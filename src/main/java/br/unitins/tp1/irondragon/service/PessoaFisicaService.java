package br.unitins.tp1.irondragon.service;

import br.unitins.tp1.irondragon.dto.request.PessoaFisicaRequestDTO;
import br.unitins.tp1.irondragon.model.PessoaFisica;

import java.util.List;

public interface PessoaFisicaService {
    public PessoaFisica findById(Long id);
    public List<PessoaFisica> findAll();
    public List<PessoaFisica> findByNome(String nome);
    public PessoaFisica create(PessoaFisicaRequestDTO dto);
    public void update(Long id, PessoaFisicaRequestDTO dto);
    public void delete(Long id);
}
