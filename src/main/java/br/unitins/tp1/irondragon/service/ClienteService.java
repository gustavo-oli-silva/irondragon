package br.unitins.tp1.irondragon.service;

import br.unitins.tp1.irondragon.dto.request.ClienteRequestDTO;
import br.unitins.tp1.irondragon.model.Usuario;

import java.util.List;

public interface ClienteService {
    public Usuario findById(Long id);

    public List<Usuario> findByNome(String nome);

    public List<Usuario> findAll();

    public Usuario create(ClienteRequestDTO dto);

    public void update(Long id, ClienteRequestDTO dto);

    public void delete(Long id);
}
