package br.unitins.tp1.irondragon.service.cidade;

import java.util.List;

import br.unitins.tp1.irondragon.dto.request.CidadeRequestDTO;
import br.unitins.tp1.irondragon.model.Cidade;
import br.unitins.tp1.irondragon.model.Estado;

public interface CidadeService {
    public Cidade findById(Long id);

    List<Cidade> findByNome(String nome);

    List<Cidade> findByNome(String nome, Integer page, Integer pageSize);

    public List<Cidade> findByEstado(Long idEstado);

    public List<Cidade> findAll();

    List<Cidade> findAll(Integer page, Integer pageSize);

    public Cidade create(CidadeRequestDTO dto);

    public void update(Long id, CidadeRequestDTO dto);

    public void delete(Long id);

    Long count();

    Long count(String nome);
}