package br.unitins.tp1.irondragon.service;

import java.util.List;

import br.unitins.tp1.irondragon.dto.request.CidadeRequestDTO;
import br.unitins.tp1.irondragon.model.Cidade;
import br.unitins.tp1.irondragon.model.Estado;

public interface CidadeService {
    public Cidade findById(Long id);

    public List<Cidade> findByNome(String nome);

    public List<Cidade> findByEstado(Estado estado);

    public List<Cidade> findAll();

    public Cidade create(CidadeRequestDTO cidade);

    public void update(Long id, CidadeRequestDTO cidade);

    public void delete(Long id);
}