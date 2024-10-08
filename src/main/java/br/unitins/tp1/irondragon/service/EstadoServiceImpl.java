package br.unitins.tp1.irondragon.service;

import java.util.List;

import br.unitins.tp1.irondragon.dto.request.EstadoRequestDTO;
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
    public Estado findById(Long id) {
        return estadoRepository.findById(id);
    }

    @Override
    public List<Estado> findByNome(String nome) {
        return estadoRepository
                .findByNome(nome);
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
    public List<Estado> findAll() {
        return estadoRepository
                .findAll()
                .list();
    }

}
