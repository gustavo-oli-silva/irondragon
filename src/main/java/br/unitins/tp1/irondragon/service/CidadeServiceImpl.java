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
    public Cidade findById(Long id) {
        return cidadeRepository.findById(id);
    }

    @Override
    public List<CidadeResponseDTO> findByNome(String nome) {
        return cidadeRepository
                .findByNome(nome)
                .stream()
                .map(a -> CidadeResponseDTO.valueOf(a))
                .toList();
    }

    @Override
    public List<CidadeResponseDTO> findByEstado(Estado estado) {
        return cidadeRepository
                .findByEstado(estado)
                .stream()
                .map(a -> CidadeResponseDTO.valueOf(a))
                .toList();
    }

    @Override
    public List<CidadeResponseDTO> findAll() {
        return cidadeRepository
                .findAll()
                .list()
                .stream()
                .map(a -> CidadeResponseDTO.valueOf(a))
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
    public CidadeResponseDTO update(Long id, CidadeRequestDTO cidade) {
        Cidade c = cidadeRepository.findById(id);
        c.setNome(cidade.nome());
        c.setEstado(estadoRepository.findById(cidade.estado()));

        return CidadeResponseDTO.valueOf(c);
    }

    @Override
    public void delete(Long id) {
        
    }
    
}
