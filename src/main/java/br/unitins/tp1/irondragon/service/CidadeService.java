package br.unitins.tp1.irondragon.service;

import java.util.List;

import br.unitins.tp1.irondragon.dto.CidadeRequestDTO;
import br.unitins.tp1.irondragon.dto.CidadeResponseDTO;
import br.unitins.tp1.irondragon.model.Cidade;
import br.unitins.tp1.irondragon.model.Estado;

public interface CidadeService {
    public Cidade findById(Long id);

    public List<CidadeResponseDTO> findByNome(String nome);

    public List<CidadeResponseDTO> findByEstado(Estado estado);

    public List<CidadeResponseDTO> findAll();

    public CidadeResponseDTO create(CidadeRequestDTO cidade);

    public CidadeResponseDTO update(Long id, CidadeRequestDTO cidade);

    public void delete(Long id);
}