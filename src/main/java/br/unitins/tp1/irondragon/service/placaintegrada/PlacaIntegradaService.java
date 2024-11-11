package br.unitins.tp1.irondragon.service.placaintegrada;

import br.unitins.tp1.irondragon.dto.request.processador.PlacaIntegradaRequestDTO;
import br.unitins.tp1.irondragon.model.processador.PlacaIntegrada;

import java.util.List;

public interface PlacaIntegradaService {
    public PlacaIntegrada findById(Long id);

    public List<PlacaIntegrada> findByNome(String nome);

    public List<PlacaIntegrada> findAll();

    public PlacaIntegrada create(PlacaIntegradaRequestDTO dto);

    public void update(Long id, PlacaIntegradaRequestDTO dto);

    public void delete(Long id);
}
