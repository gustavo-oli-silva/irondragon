package br.unitins.tp1.irondragon.service;

import br.unitins.tp1.irondragon.dto.FabricanteRequestDTO;
import br.unitins.tp1.irondragon.dto.FabricanteResponseDTO;
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
    public FabricanteResponseDTO findById(Long id) {
        return FabricanteResponseDTO.valueOf(fabricanteRepository.findById(id));
    }

    @Override
    public List<FabricanteResponseDTO> findByNome(String nome) {
        return fabricanteRepository
                .findByNome(nome)
                .stream()
                .map(FabricanteResponseDTO::valueOf)
                .toList();
    }

    @Override
    public List<FabricanteResponseDTO> findAll() {
        return fabricanteRepository
                .findAll()
                .stream()
                .map(FabricanteResponseDTO::valueOf)
                .toList();
    }

    @Transactional
    @Override
    public FabricanteResponseDTO create(FabricanteRequestDTO dto) {
        Fabricante fabricante = new Fabricante();

        fabricante.setNome(dto.nome());
        fabricante.setEmail(dto.email());
        fabricanteRepository.persist(fabricante);

        return FabricanteResponseDTO.valueOf(fabricante);
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
