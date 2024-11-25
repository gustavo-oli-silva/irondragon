package br.unitins.tp1.irondragon.service.cliente;

import br.unitins.tp1.irondragon.dto.request.usuario.ClienteRequestDTO;
import br.unitins.tp1.irondragon.dto.request.usuario.UsuarioRequestDTO;
import br.unitins.tp1.irondragon.model.usuario.Cliente;

import java.util.List;

public interface ClienteService {
    Cliente findById(Long id);

    Cliente findByIdUsuario(Long idUsuario);

    List<Cliente> findAll();

    Cliente create(String username, ClienteRequestDTO dto);

    public Cliente findByUsername(String username);

    void delete(Long id);
}
