package br.unitins.tp1.irondragon.service;

import java.util.List;

import br.unitins.tp1.irondragon.dto.ProcessadorRequestDTO;
import br.unitins.tp1.irondragon.dto.ProcessadorResponseDTO;
import br.unitins.tp1.irondragon.model.Processador;
import br.unitins.tp1.irondragon.repository.ProcessadorRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class ProcessadorServiceImpl implements ProcessadorService {
    @Inject
    public ProcessadorRepository processadorRepository;

    @Override
    public Processador findById(Long id) {
        return processadorRepository.findById(id);
    }

    @Override
    public List<ProcessadorResponseDTO> findByNome(String nome) {
        return processadorRepository
            .findByNome(nome)
            .stream()
            .map(a -> ProcessadorResponseDTO.valueOf(a))
            .toList();
    }

    @Transactional
    @Override
    public ProcessadorResponseDTO create(ProcessadorRequestDTO processador) {
        Processador p = new Processador();
        p.setNome(processador.nome());
        p.setSocket(processador.socket());
        p.setThreads(processador.threads());
        p.setNucleos(processador.nucleos());
        p.setDesbloqueado(processador.desbloqueado());
        p.setPreco(processador.preco());

        processadorRepository.persist(p);

        return ProcessadorResponseDTO.valueOf(p);
    }

    @Transactional
    @Override
    public ProcessadorResponseDTO update(Long id, ProcessadorRequestDTO processador) {
        Processador p = processadorRepository.findById(id);
        p.setNome(processador.nome());
        p.setNucleos(processador.nucleos());
        p.setPreco(processador.preco());
        p.setThreads(processador.threads());
        p.setDesbloqueado(processador.desbloqueado());
        p.setSocket(processador.socket());

        return ProcessadorResponseDTO.valueOf(p);
    }

    @Transactional
    @Override
    public void delete(Long id) {
        processadorRepository.deleteById(id);
    }
    
}
