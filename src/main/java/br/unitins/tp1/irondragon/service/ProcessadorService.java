package br.unitins.tp1.irondragon.service;

import java.util.List;

import br.unitins.tp1.irondragon.dto.ProcessadorRequestDTO;
import br.unitins.tp1.irondragon.model.Processador;

public interface ProcessadorService {
    public Processador findById(Long id);
    public List<Processador> findAll();
    public List<Processador> findByNome(String nome);
    public Processador create(ProcessadorRequestDTO processador);
    public void update(Long id, ProcessadorRequestDTO processador);
    public void delete(Long id);
}
