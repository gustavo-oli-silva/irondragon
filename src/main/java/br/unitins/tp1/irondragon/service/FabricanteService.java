package br.unitins.tp1.irondragon.service;

import br.unitins.tp1.irondragon.dto.request.FabricanteRequestDTO;
import br.unitins.tp1.irondragon.model.Fabricante;

import java.util.List;

public interface FabricanteService {
    public Fabricante findById(Long id);

    public List<Fabricante> findByNome(String nome);

    public List<Fabricante> findAll();

    public Fabricante create(FabricanteRequestDTO cidade);

    public void update(Long id, FabricanteRequestDTO cidade);

    public void delete(Long id);
}
