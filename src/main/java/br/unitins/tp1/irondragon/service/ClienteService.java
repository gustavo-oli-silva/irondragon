package br.unitins.tp1.irondragon.service;

import br.unitins.tp1.irondragon.dto.request.ClienteRequestDTO;
import br.unitins.tp1.irondragon.model.Cliente;

import java.util.List;

public interface ClienteService {
    public Cliente findById(Long id);

    public List<Cliente> findByNome(String nome);

    public List<Cliente> findAll();

    public Cliente create(ClienteRequestDTO dto);

    public void update(Long id, ClienteRequestDTO dto);

    public void delete(Long id);
}
