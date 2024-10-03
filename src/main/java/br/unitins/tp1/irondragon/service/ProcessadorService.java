package br.unitins.tp1.irondragon.service;

import java.util.List;

import br.unitins.tp1.irondragon.dto.ProcessadorRequestDTO;
import br.unitins.tp1.irondragon.dto.ProcessadorResponseDTO;
import br.unitins.tp1.irondragon.model.Processador;

public interface ProcessadorService {
    public ProcessadorResponseDTO findById(Long id);
    public List<ProcessadorResponseDTO> findAll();
    public List<ProcessadorResponseDTO> findByNome(String nome);
    public ProcessadorResponseDTO create(ProcessadorRequestDTO processador);
    public void update(Long id, ProcessadorRequestDTO processador);
    public void delete(Long id);
}
