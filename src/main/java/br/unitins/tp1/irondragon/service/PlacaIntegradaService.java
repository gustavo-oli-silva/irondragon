package br.unitins.tp1.irondragon.service;

import br.unitins.tp1.irondragon.dto.request.PlacaIntegradaRequestDTO;
import br.unitins.tp1.irondragon.model.PlacaIntegrada;

import java.util.List;

public interface PlacaIntegradaService {
    public PlacaIntegrada findById(Long id);

    public List<PlacaIntegrada> findByNome(String nome);

    public List<PlacaIntegrada> findAll();

    public PlacaIntegrada create(PlacaIntegradaRequestDTO dto);

    public void update(Long id, PlacaIntegradaRequestDTO dto);

    public void delete(Long id);
}
