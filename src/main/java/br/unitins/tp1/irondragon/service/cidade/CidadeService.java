package br.unitins.tp1.irondragon.service.cidade;

import java.util.List;

import br.unitins.tp1.irondragon.dto.request.CidadeRequestDTO;
import br.unitins.tp1.irondragon.model.Cidade;
import br.unitins.tp1.irondragon.model.Estado;

public interface CidadeService {
    public Cidade findById(Long id);

    public List<Cidade> findByNome(String nome);

    public List<Cidade> findByEstado(Estado estado);

    public List<Cidade> findAll();

    public Cidade create(CidadeRequestDTO dto);

    public void update(Long id, CidadeRequestDTO dto);

    public void delete(Long id);
}