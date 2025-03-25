package br.unitins.tp1.irondragon.service.estado;

import java.util.List;

import br.unitins.tp1.irondragon.dto.request.EstadoRequestDTO;
import br.unitins.tp1.irondragon.model.Estado;

public interface EstadoService {
    public Estado findById(Long id);

    public Long count();
    public List<Estado> findByNome(String nome, Integer page, Integer pageSize);

    public List<Estado> findAll(Integer page, Integer pageSize);

    public Estado create(EstadoRequestDTO dto);

    public void update(Long id, EstadoRequestDTO dto);

    public void delete(Long id);
}
