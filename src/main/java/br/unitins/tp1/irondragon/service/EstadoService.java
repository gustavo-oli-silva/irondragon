package br.unitins.tp1.irondragon.service;

import java.util.List;

import br.unitins.tp1.irondragon.dto.EstadoRequestDTO;
import br.unitins.tp1.irondragon.dto.EstadoResponseDTO;
import br.unitins.tp1.irondragon.model.Estado;

public interface EstadoService {
    public Estado findById(Long id);

    public List<Estado> findByNome(String nome);

    public List<Estado> findAll();

    public Estado create(EstadoRequestDTO estado);

    public void update(Long id, EstadoRequestDTO estado);

    public void delete(Long id);
}
