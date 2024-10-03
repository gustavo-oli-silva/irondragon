package br.unitins.tp1.irondragon.service;

import java.util.List;

import br.unitins.tp1.irondragon.dto.EstadoRequestDTO;
import br.unitins.tp1.irondragon.dto.EstadoResponseDTO;
import br.unitins.tp1.irondragon.model.Estado;
import br.unitins.tp1.irondragon.repository.EstadoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class EstadoServiceImpl implements EstadoService {

    @Inject
    public EstadoRepository estadoRepository;

    @Override
    public EstadoResponseDTO findById(Long id) {
        return EstadoResponseDTO.valueOf(estadoRepository.findById(id));
    }

    @Override
    public List<EstadoResponseDTO> findByNome(String nome) {
        return estadoRepository
                .findByNome(nome)
                .stream()
                .map(EstadoResponseDTO::valueOf)
                .toList();
    }

    @Transactional
    @Override
    public Estado create(EstadoRequestDTO dto) {
        Estado e = new Estado();
        e.setNome(dto.nome());
        e.setSigla(dto.sigla());

        estadoRepository.persist(e);

        return e;
    }

    @Transactional
    @Override
    public void update(Long id, EstadoRequestDTO estado) {
        Estado e = estadoRepository.findById(id);
        e.setNome(estado.nome());
        e.setSigla(estado.sigla());
    }

    @Transactional
    @Override
    public void delete(Long id) {
        estadoRepository.deleteById(id);
    }

    @Override
    public List<EstadoResponseDTO> findAll() {
        return estadoRepository
                .findAll()
                .list()
                .stream()
                .map(EstadoResponseDTO::valueOf)
                .toList();
    }

}
