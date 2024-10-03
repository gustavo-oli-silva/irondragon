package br.unitins.tp1.irondragon.service;

import java.util.List;

import br.unitins.tp1.irondragon.dto.CidadeRequestDTO;
import br.unitins.tp1.irondragon.dto.CidadeResponseDTO;
import br.unitins.tp1.irondragon.model.Cidade;
import br.unitins.tp1.irondragon.model.Estado;
import br.unitins.tp1.irondragon.repository.CidadeRepository;
import br.unitins.tp1.irondragon.repository.EstadoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class CidadeServiceImpl implements CidadeService {
    @Inject
    public CidadeRepository cidadeRepository;

    @Inject
    public EstadoRepository estadoRepository;

    @Override
    public CidadeResponseDTO findById(Long id) {
        return CidadeResponseDTO.valueOf(cidadeRepository.findById(id));
    }

    @Override
    public List<CidadeResponseDTO> findByNome(String nome) {
        return cidadeRepository
                .findByNome(nome)
                .stream()
                .map(CidadeResponseDTO::valueOf)
                .toList();
    }

    @Override
    public List<CidadeResponseDTO> findByEstado(Estado estado) {
        return cidadeRepository
                .findByEstado(estado)
                .stream()
                .map(CidadeResponseDTO::valueOf)
                .toList();
    }

    @Override
    public List<CidadeResponseDTO> findAll() {
        return cidadeRepository
                .findAll()
                .list()
                .stream()
                .map(CidadeResponseDTO::valueOf)
                .toList();
    }

    @Transactional
    @Override
    public CidadeResponseDTO create(CidadeRequestDTO cidade) {
        Cidade c = new Cidade();
        c.setNome(cidade.nome());
        c.setEstado(estadoRepository.findById(cidade.estado()));
        cidadeRepository.persist(c);
        return CidadeResponseDTO.valueOf(c);
    }

    @Transactional
    @Override
    public void update(Long id, CidadeRequestDTO cidade) {
        Cidade c = cidadeRepository.findById(id);
        c.setNome(cidade.nome());
        c.setEstado(estadoRepository.findById(cidade.estado()));
    }

    @Override
    public void delete(Long id) {
        cidadeRepository.deleteById(id);
    }
    
}
