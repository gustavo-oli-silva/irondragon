package br.unitins.tp1.irondragon.service.fabricante;

import br.unitins.tp1.irondragon.dto.request.FabricanteRequestDTO;
import br.unitins.tp1.irondragon.model.Fabricante;
import br.unitins.tp1.irondragon.repository.FabricanteRepository;
import br.unitins.tp1.irondragon.validation.ValidationException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
public class FabricanteServiceImpl implements FabricanteService {
    @Inject
    public FabricanteRepository fabricanteRepository;

    @Override
    public Fabricante findById(Long id) {
        Fabricante fabricante = fabricanteRepository.findById(id);

        if(fabricante == null) {
            throw new ValidationException("id", "Fabricante informado não existe");
        }

        return fabricante;
    }

    @Override
    public List<Fabricante> findByNome(String nome) {
        return fabricanteRepository
                .findByNome(nome);
    }

    @Override
    public List<Fabricante> findAll() {
        return fabricanteRepository
                .findAll()
                .list();
    }

    @Transactional
    @Override
    public Fabricante create(FabricanteRequestDTO dto) {
        Fabricante fabricante = new Fabricante();

        fabricante.setNome(dto.nome());
        fabricante.setEmail(dto.email());
        fabricante.setTelefone(dto.telefone().toEntityTelefoneFabricante());
        fabricanteRepository.persist(fabricante);

        return fabricante;
    }

    @Transactional
    @Override
    public void update(Long id, FabricanteRequestDTO dto) {
        Fabricante fabricante = fabricanteRepository.findById(id);

        if(fabricante == null) {
            throw new ValidationException("id", "Fabricante informado não existe");
        }

        fabricante.setNome(dto.nome());
        fabricante.setEmail(dto.email());
        fabricante.setTelefone(dto.telefone().toEntityTelefoneFabricante());
    }

    @Transactional
    @Override
    public void delete(Long id) {
        Fabricante fabricante = fabricanteRepository.findById(id);

        if(fabricante == null) {
            throw new ValidationException("id", "Fabricante informado não existe");
        }

        fabricanteRepository.deleteById(id);
    }
}
