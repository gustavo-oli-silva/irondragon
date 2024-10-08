package br.unitins.tp1.irondragon.service;

import br.unitins.tp1.irondragon.dto.request.FabricanteRequestDTO;
import br.unitins.tp1.irondragon.model.Fabricante;
import br.unitins.tp1.irondragon.repository.FabricanteRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
public class FabricanteServiceImpl implements FabricanteService {
    @Inject
    public FabricanteRepository fabricanteRepository;

    @Override
    public Fabricante findById(Long id) {
        return fabricanteRepository.findById(id);
    }

    @Override
    public List<Fabricante> findByNome(String nome) {
        return fabricanteRepository
                .findByNome(nome);
    }

    @Override
    public List<Fabricante> findAll() {
        return fabricanteRepository
                .findAll()
                .list();
    }

    @Transactional
    @Override
    public Fabricante create(FabricanteRequestDTO dto) {
        Fabricante fabricante = new Fabricante();

        fabricante.setNome(dto.nome());
        fabricante.setEmail(dto.email());
        fabricanteRepository.persist(fabricante);

        return fabricante;
    }

    @Transactional
    @Override
    public void update(Long id, FabricanteRequestDTO dto) {
        Fabricante fabricante = fabricanteRepository.findById(id);

        fabricante.setNome(dto.nome());
        fabricante.setEmail(dto.email());
    }

    @Transactional
    @Override
    public void delete(Long id) {
        fabricanteRepository.deleteById(id);
    }
}
