package br.unitins.tp1.irondragon.service.placaintegrada;

import br.unitins.tp1.irondragon.dto.request.processador.PlacaIntegradaRequestDTO;
import br.unitins.tp1.irondragon.model.processador.PlacaIntegrada;
import br.unitins.tp1.irondragon.repository.PlacaIntegradaRepository;
import br.unitins.tp1.irondragon.validation.ValidationException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
public class PlacaIntegradaServiceImpl implements PlacaIntegradaService {
    @Inject
    public PlacaIntegradaRepository placaIntegradaRepository;

    @Override
    public PlacaIntegrada findById(Long id) {
        return placaIntegradaRepository.findById(id);
    }

    @Override
    public List<PlacaIntegrada> findByNome(String nome) {
        return placaIntegradaRepository.findByNome(nome);
    }

    @Override
    public List<PlacaIntegrada> findAll() {
        return placaIntegradaRepository.findAll().list();
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
}
