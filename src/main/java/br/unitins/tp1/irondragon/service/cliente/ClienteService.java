package br.unitins.tp1.irondragon.service.cliente;

import br.unitins.tp1.irondragon.model.processador.Processador;
import br.unitins.tp1.irondragon.model.usuario.Cliente;

import java.util.List;

public interface ClienteService {
    Cliente findById(Long id);

    Cliente findByIdUsuario(Long idUsuario);

    List<Cliente> findAll();

    Cliente create(String username);

    List<Processador> getListaDeDesejos(String username);

    Processador addToListaDeDesejos(Long idProcessador, String username);

    void removeFromListaDeDesejos(Long idProcessador, String username);

    public Cliente findByUsername(String username);

    void delete(Long id);
}
