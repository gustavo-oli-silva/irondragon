package br.unitins.tp1.irondragon.service;

import br.unitins.tp1.irondragon.dto.FabricanteRequestDTO;
import br.unitins.tp1.irondragon.dto.FabricanteResponseDTO;
import br.unitins.tp1.irondragon.model.Estado;

import java.util.List;

public interface FabricanteService {
    public FabricanteResponseDTO findById(Long id);

    public List<FabricanteResponseDTO> findByNome(String nome);

    public List<FabricanteResponseDTO> findAll();

    public FabricanteResponseDTO create(FabricanteRequestDTO cidade);

    public void update(Long id, FabricanteRequestDTO cidade);

    public void delete(Long id);
}
