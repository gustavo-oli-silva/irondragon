package br.unitins.tp1.irondragon.service.fabricante;

import br.unitins.tp1.irondragon.dto.request.FabricanteRequestDTO;
import br.unitins.tp1.irondragon.model.Fabricante;

import java.util.List;

public interface FabricanteService {
    public Fabricante findById(Long id);

    public List<Fabricante> findByNome(String nome);

    List<Fabricante> findByNome(String nome, Integer page, Integer pageSize);

    public List<Fabricante> findAll();

    List<Fabricante> findAll(Integer page, Integer pageSize);

    public Fabricante create(FabricanteRequestDTO fabricante);

    public void update(Long id, FabricanteRequestDTO fabricante);

    public void delete(Long id);

    Long count();

    Long count(String nome);
}
