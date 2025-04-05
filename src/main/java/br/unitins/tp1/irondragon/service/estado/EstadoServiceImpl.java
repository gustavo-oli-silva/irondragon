package br.unitins.tp1.irondragon.service.estado;

import java.util.List;

import br.unitins.tp1.irondragon.dto.request.EstadoRequestDTO;
import br.unitins.tp1.irondragon.model.Estado;
import br.unitins.tp1.irondragon.repository.EstadoRepository;
import br.unitins.tp1.irondragon.validation.ValidationException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class EstadoServiceImpl implements EstadoService {

    @Inject
    public EstadoRepository estadoRepository;

    @Override
    public Estado findById(Long id) {
        Estado estado = estadoRepository.findById(id);

        if (estado == null) {
            throw new ValidationException("id", "Estado não existente!");
        }

        return estado;
    }

    @Override
    public List<Estado> findByNome(String nome, Integer page, Integer pageSize) {
        return estadoRepository
                .findByNome(nome)
                .page(page, pageSize)
                .list();
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
    public void update(Long id, EstadoRequestDTO dto) {
        Estado estado = estadoRepository.findById(id);

        if (estado == null) {
            throw new ValidationException("id", "Fabricante informado não existe");
        }

        estado.setNome(dto.nome());
        estado.setSigla(dto.sigla());
    }

    @Transactional
    @Override
    public void delete(Long id) {
        Estado estado = estadoRepository.findById(id);

        if (estado == null) {
            throw new ValidationException("id", "Fabricante informado não existe");
        }

        estadoRepository.deleteById(id);
    }

    @Override
    public List<Estado> findAll(Integer page, Integer pageSize) {
        return estadoRepository
                .findAll()
                .page(page, pageSize)
                .list();
    }

    @Override
    public Long count() {
        return estadoRepository.findAll().count();
    }

    @Override
    public Long count(String nome) {
        return estadoRepository.findByNome(nome).count();
    }
}
