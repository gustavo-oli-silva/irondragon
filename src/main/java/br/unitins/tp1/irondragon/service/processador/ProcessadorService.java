package br.unitins.tp1.irondragon.service.processador;

import java.util.List;

import br.unitins.tp1.irondragon.dto.request.ProcessadorFilterRequest;
import br.unitins.tp1.irondragon.dto.request.processador.ProcessadorRequestDTO;
import br.unitins.tp1.irondragon.model.ImagemProcessador;
import br.unitins.tp1.irondragon.model.processador.Processador;

public interface ProcessadorService {
    public Processador findById(Long id);

    public List<Processador> findAll();

    List<Processador> findAll(Integer page, Integer pageSize);

    public List<Processador> findByNome(String nome);

    List<Processador> findByNome(String nome, Integer page, Integer pageSize);

    public Processador create(ProcessadorRequestDTO processador);

    public void update(Long id, ProcessadorRequestDTO processador);

    public void delete(Long id);

    public Processador updateNomeImagem(Long id, ImagemProcessador imagemProcessador);

    Long count();

    Long count(String nome);

    List<Processador> findByFiltros(Integer page, Integer pageSize, ProcessadorFilterRequest filtros);

    Long count(ProcessadorFilterRequest filtros);
}
