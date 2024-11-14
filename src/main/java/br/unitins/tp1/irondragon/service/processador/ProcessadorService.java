package br.unitins.tp1.irondragon.service.processador;

import java.util.List;

import br.unitins.tp1.irondragon.dto.request.processador.ProcessadorRequestDTO;
import br.unitins.tp1.irondragon.model.processador.Processador;

public interface ProcessadorService {
    public Processador findById(Long id);
    public List<Processador> findAll();
    public List<Processador> findByNome(String nome);
    public Processador create(ProcessadorRequestDTO processador);
    public void update(Long id, ProcessadorRequestDTO processador);
    public void delete(Long id);

    public Processador updateNomeImagem(Long id, String nomeImagem);
}
