package br.unitins.tp1.irondragon.service;

import java.util.List;

import br.unitins.tp1.irondragon.dto.request.ProcessadorRequestDTO;
import br.unitins.tp1.irondragon.model.Processador;
import br.unitins.tp1.irondragon.repository.ProcessadorRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class ProcessadorServiceImpl implements ProcessadorService {
    @Inject
    public ProcessadorRepository processadorRepository;

    @Inject
    public FabricanteService fabricanteService;

    @Override
    public Processador findById(Long id) {
        return processadorRepository.findById(id);
    }

    @Override
    public List<Processador> findAll() {
        return processadorRepository
                .findAll()
                .list();
    }

    @Override
    public List<Processador> findByNome(String nome) {
        return processadorRepository
            .findByNome(nome);
    }

    @Transactional
    @Override
    public Processador create(ProcessadorRequestDTO dto) {
        Processador processador = new Processador();
        processador.setNome(dto.nome());
        processador.setSocket(dto.socket());
        processador.setThreads(dto.threads());
        processador.setNucleos(dto.nucleos());
        processador.setDesbloqueado(dto.desbloqueado());
        processador.setPreco(dto.preco());
        processador.setFabricante(fabricanteService.findById(dto.fabricante()));

        processadorRepository.persist(processador);

        return processador;
    }

    @Transactional
    @Override
    public void update(Long id, ProcessadorRequestDTO dto) {
        Processador processador = processadorRepository.findById(id);
        processador.setNome(dto.nome());
        processador.setNucleos(dto.nucleos());
        processador.setPreco(dto.preco());
        processador.setThreads(dto.threads());
        processador.setDesbloqueado(dto.desbloqueado());
        processador.setSocket(dto.socket());
        processador.setFabricante(fabricanteService.findById(dto.fabricante()));
    }

    @Transactional
    @Override
    public void delete(Long id) {
        Processador p = processadorRepository.findById(id);
        processadorRepository.deleteById(id);
    }
    
}
