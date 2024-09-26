package br.unitins.tp1.irondragon.service;

import java.util.List;

import br.unitins.tp1.irondragon.dto.ProcessadorRequestDTO;
import br.unitins.tp1.irondragon.dto.ProcessadorResponseDTO;
import br.unitins.tp1.irondragon.model.Processador;

public interface ProcessadorService {
    public Processador findById(Long id);
    public List<ProcessadorResponseDTO> findByNome(String nome);
    public ProcessadorResponseDTO create(ProcessadorRequestDTO processador);
    public ProcessadorResponseDTO update(Long id, ProcessadorRequestDTO processador);
    public void delete(Long id);
}
