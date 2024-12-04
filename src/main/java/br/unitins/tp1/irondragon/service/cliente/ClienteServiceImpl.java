package br.unitins.tp1.irondragon.service.cliente;

import br.unitins.tp1.irondragon.model.processador.Processador;
import br.unitins.tp1.irondragon.model.usuario.Cliente;
import br.unitins.tp1.irondragon.model.usuario.Usuario;
import br.unitins.tp1.irondragon.repository.ClienteRepository;
import br.unitins.tp1.irondragon.service.processador.ProcessadorService;
import br.unitins.tp1.irondragon.service.usuario.UsuarioService;
import br.unitins.tp1.irondragon.validation.ValidationException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class ClienteServiceImpl implements ClienteService {
    @Inject
    public ClienteRepository clienteRepository;

    @Inject
    public UsuarioService usuarioService;

    @Inject
    public ProcessadorService processadorService;

    @Override
    public Cliente findById(Long id) {
        return clienteRepository.findById(id);
    }

    @Override
    public Cliente findByIdUsuario(Long idUsuario) {
        return clienteRepository.findClienteByIdUsuario(idUsuario);
    }

    @Override
    public List<Cliente> findAll() {
        return clienteRepository.listAll();
    }

    @Transactional
    @Override
    public Cliente create(String username) {
        Usuario usuario = usuarioService.findByUsername(username);

        Cliente cliente = new Cliente();
        cliente.setUsuario(usuario);
        cliente.setListaDeCartoes(new ArrayList<>());
        cliente.setListaDeDesejos(new ArrayList<>());

        clienteRepository.persist(cliente);

        return cliente;
    }

    @Override
    public List<Processador> getListaDeDesejos(String username) {
        Cliente cliente = clienteRepository.findByUsername(username);

        return cliente.getListaDeDesejos();
    }

    @Transactional
    @Override
    public Processador addToListaDeDesejos(Long idProcessador, String username) {
        Cliente cliente = clienteRepository.findByUsername(username);
        Processador processador = processadorService.findById(idProcessador);

        cliente.getListaDeDesejos().add(processador);

        return processador;
    }

    @Transactional
    @Override
    public void removeFromListaDeDesejos(Long idProcessador, String username) {
        Cliente cliente = clienteRepository.findByUsername(username);
        Processador processador = processadorService.findById(idProcessador);

        cliente.getListaDeDesejos().remove(processador);
    }

    @Override
    public Cliente findByUsername(String username) {
        Cliente cliente = clienteRepository.findByUsername(username);

        return cliente;
    }

    @Override
    public void delete(Long id) {
        Cliente cliente = clienteRepository.findById(id);

        if(cliente == null) {
            throw new ValidationException("id", "Cliente informado não existe!");
        }

        clienteRepository.deleteById(id);
    }
}
