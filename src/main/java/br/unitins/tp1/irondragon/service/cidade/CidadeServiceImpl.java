package br.unitins.tp1.irondragon.service.cidade;

import java.util.List;

import br.unitins.tp1.irondragon.dto.request.CidadeRequestDTO;
import br.unitins.tp1.irondragon.model.Cidade;
import br.unitins.tp1.irondragon.model.Estado;
import br.unitins.tp1.irondragon.repository.CidadeRepository;
import br.unitins.tp1.irondragon.service.estado.EstadoService;
import br.unitins.tp1.irondragon.validation.ValidationException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class CidadeServiceImpl implements CidadeService {
    @Inject
    public CidadeRepository cidadeRepository;

    @Inject
    public EstadoService estadoService;

    @Override
    public Cidade findById(Long id) {
        return cidadeRepository.findById(id);
    }

    @Override
    public List<Cidade> findByNome(String nome) {
        return cidadeRepository
                .findByNome(nome);
    }

    @Override
    public List<Cidade> findByEstado(Estado estado) {
        return cidadeRepository
                .findByEstado(estado);
    }

    @Override
    public List<Cidade> findAll() {
        return cidadeRepository
                .findAll()
                .list();
    }

    @Transactional
    @Override
    public Cidade create(CidadeRequestDTO cidade) {
        Cidade c = new Cidade();
        c.setNome(cidade.nome());
        c.setEstado(estadoService.findById(cidade.estado()));
        cidadeRepository.persist(c);
        return c;
    }

    @Transactional
    @Override
    public void update(Long id, CidadeRequestDTO cidade) {
        Cidade c = cidadeRepository.findById(id);

        if(c == null) {
            throw new ValidationException("id", "A cidade informada não existe!");
        }

        c.setNome(cidade.nome());
        c.setEstado(estadoService.findById(cidade.estado()));
    }

    @Transactional
    @Override
    public void delete(Long id) {
        Cidade cidade = cidadeRepository.findById(id);

        if(cidade == null) {
            throw new ValidationException("id", "Cidade informada não existe!");
        }

        cidadeRepository.deleteById(id);
    }
    
}
