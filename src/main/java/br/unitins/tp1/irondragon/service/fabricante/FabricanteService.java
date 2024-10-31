package br.unitins.tp1.irondragon.service.fabricante;

import br.unitins.tp1.irondragon.dto.request.FabricanteRequestDTO;
import br.unitins.tp1.irondragon.model.Fabricante;

import java.util.List;

public interface FabricanteService {
    public Fabricante findById(Long id);

    public List<Fabricante> findByNome(String nome);

    public List<Fabricante> findAll();

    public Fabricante create(FabricanteRequestDTO fabricante);

    public void update(Long id, FabricanteRequestDTO fabricante);

    public void delete(Long id);
}
