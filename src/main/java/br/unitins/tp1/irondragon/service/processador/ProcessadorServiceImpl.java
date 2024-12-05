package br.unitins.tp1.irondragon.service.processador;

import java.util.List;

import br.unitins.tp1.irondragon.dto.request.processador.ProcessadorRequestDTO;
import br.unitins.tp1.irondragon.model.processador.Conectividade;
import br.unitins.tp1.irondragon.model.processador.Processador;
import br.unitins.tp1.irondragon.repository.ProcessadorRepository;
import br.unitins.tp1.irondragon.service.fabricante.FabricanteService;
import br.unitins.tp1.irondragon.service.placaintegrada.PlacaIntegradaService;
import br.unitins.tp1.irondragon.validation.ValidationException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class ProcessadorServiceImpl implements ProcessadorService {
    @Inject
    public ProcessadorRepository processadorRepository;

    @Inject
    public FabricanteService fabricanteService;

    @Inject
    public PlacaIntegradaService placaIntegradaService;

    @Override
    public Processador findById(Long id) {
        Processador processador = processadorRepository.findById(id);

        if(processador == null) {
            throw new ValidationException("id", "Processador especificado não existe!");
        }

        return processador;
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
        processador.setPlacaIntegrada(placaIntegradaService.findById(dto.placaIntegrada()));
        processador.setConectividade(dto.conectividade().intoEntity());
        processador.setFrequencia(dto.frequencia().intoEntity());
        processador.setMemoriaCache(dto.memoriaCache().intoEntity());
        processador.setConsumoEnergetico(dto.consumoEnergetico().intoEntity());

        processadorRepository.persist(processador);

        return processador;
    }

    @Transactional
    @Override
    public void update(Long id, ProcessadorRequestDTO dto) {
        Processador processador = processadorRepository.findById(id);

        if(processador == null) {
            throw new ValidationException("id", "Processador especificado não existe!");
        }

        processador.setNome(dto.nome());
        processador.setNucleos(dto.nucleos());
        processador.setPreco(dto.preco());
        processador.setThreads(dto.threads());
        processador.setDesbloqueado(dto.desbloqueado());
        processador.setSocket(dto.socket());
        processador.setPlacaIntegrada(placaIntegradaService.findById(dto.placaIntegrada()));
        processador.setFabricante(fabricanteService.findById(dto.fabricante()));
        processador.setConectividade(dto.conectividade().intoEntity());
        processador.setFrequencia(dto.frequencia().intoEntity());
        processador.setMemoriaCache(dto.memoriaCache().intoEntity());
        processador.setConsumoEnergetico(dto.consumoEnergetico().intoEntity());
    }

    @Transactional
    @Override
    public void delete(Long id) {
        Processador p = processadorRepository.findById(id);

        if(p == null) {
            throw new ValidationException("id", "Processador especificado não existe!");
        }

        processadorRepository.deleteById(id);
    }

    @Transactional
    @Override
    public Processador updateNomeImagem(Long id, String nomeImagem) {
        Processador processador = processadorRepository.findById(id);

        if(processador == null) throw new ValidationException("id", "Processador não existe!");

        processador.getImagens().add(nomeImagem);

        return processador;
    }

}
