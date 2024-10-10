package br.unitins.tp1.irondragon.service;

import br.unitins.tp1.irondragon.dto.request.PlacaIntegradaRequestDTO;
import br.unitins.tp1.irondragon.model.PlacaIntegrada;
import br.unitins.tp1.irondragon.repository.PlacaIntegradaRepository;
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
        placaIntegrada.setNome(dto.nome());
        placaIntegrada.setDirectX(dto.directX());
        placaIntegrada.setOpenGl(dto.openGl());
        placaIntegrada.setVulkan(dto.vulkan());
    }

    @Transactional
    @Override
    public void delete(Long id) {
        placaIntegradaRepository.deleteById(id);
    }
}
