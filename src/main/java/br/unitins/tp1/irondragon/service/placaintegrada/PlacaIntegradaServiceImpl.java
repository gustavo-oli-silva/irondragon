package br.unitins.tp1.irondragon.service.placaintegrada;

import br.unitins.tp1.irondragon.dto.request.processador.PlacaIntegradaRequestDTO;
import br.unitins.tp1.irondragon.model.processador.PlacaIntegrada;
import br.unitins.tp1.irondragon.repository.PlacaIntegradaRepository;
import br.unitins.tp1.irondragon.validation.ValidationException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class PlacaIntegradaServiceImpl implements PlacaIntegradaService {
    @Inject
    public PlacaIntegradaRepository placaIntegradaRepository;

    @Override
    public Optional<PlacaIntegrada> findById(Long id) {
        if (id == null) {
            return Optional.empty(); 
        }
        return placaIntegradaRepository.findByIdOptional(id);
    }
    

    @Override
    public List<PlacaIntegrada> findByNome(String nome) {
        return placaIntegradaRepository.findByNome(nome).list();
    }

    @Override
    public List<PlacaIntegrada> findByNome(String nome, Integer page, Integer pageSize) {
        return placaIntegradaRepository
                .findByNome(nome)
                .page(page, pageSize)
                .list();
    }

    @Override
    public List<PlacaIntegrada> findAll() {
        return placaIntegradaRepository.findAll().list();
    }

    @Override
    public List<PlacaIntegrada> findAll(Integer page, Integer pageSize) {
        return placaIntegradaRepository
                .findAll()
                .page(page, pageSize)
                .list();
    }

    @Transactional
    @Override
    public PlacaIntegrada create(PlacaIntegradaRequestDTO dto) {
        PlacaIntegrada placaIntegrada = new PlacaIntegrada();

        placaIntegrada.setNome(dto.nome());
        placaIntegrada.setDirectX(dto.directX());
        placaIntegrada.setOpenGl(dto.openGl());
        placaIntegrada.setVulkan(dto.vulkan());

        placaIntegradaRepository.persist(placaIntegrada);

        return placaIntegrada;
    }

    @Transactional
    @Override
    public void update(Long id, PlacaIntegradaRequestDTO dto) {
        PlacaIntegrada placaIntegrada = placaIntegradaRepository.findById(id);

        if(placaIntegrada == null) {
            throw new ValidationException("id", "A placa integrada informada não existe!");
        }

        placaIntegrada.setNome(dto.nome());
        placaIntegrada.setDirectX(dto.directX());
        placaIntegrada.setOpenGl(dto.openGl());
        placaIntegrada.setVulkan(dto.vulkan());
    }

    @Transactional
    @Override
    public void delete(Long id) {
        PlacaIntegrada placaIntegrada = placaIntegradaRepository.findById(id);

        if(placaIntegrada == null) {
            throw new ValidationException("id", "A placa integrada informada não existe!");
        }

        placaIntegradaRepository.deleteById(id);
    }

    @Override
    public Long count() {
        return placaIntegradaRepository.count();
    }

    @Override
    public Long count(String nome) {
        return placaIntegradaRepository.findByNome(nome).count();
    }
}
