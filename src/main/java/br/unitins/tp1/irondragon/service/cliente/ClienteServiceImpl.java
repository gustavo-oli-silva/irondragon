package br.unitins.tp1.irondragon.service.cliente;

import br.unitins.tp1.irondragon.dto.request.usuario.ClienteRequestDTO;
import br.unitins.tp1.irondragon.model.usuario.Cliente;
import br.unitins.tp1.irondragon.model.usuario.Usuario;
import br.unitins.tp1.irondragon.repository.ClienteRepository;
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
    public Cliente create(String username, ClienteRequestDTO dto) {
        validarCpf(dto.cpf());

        Usuario usuario = usuarioService.findByUsername(username);

        Cliente cliente = new Cliente();
        cliente.setUsuario(usuario);
        cliente.setCpf(dto.cpf());
        cliente.setListaDeCartoes(new ArrayList<>());

        clienteRepository.persist(cliente);

        return cliente;
    }

    public void validarCpf(String cpf) {
        Cliente cliente = clienteRepository.findByCpf(cpf);

        if(cliente != null) {
            throw new ValidationException("cpf", "CPF inválido!");
        }
    }

    @Override
    public void delete(Long id) {
        clienteRepository.deleteById(id);
    }
}
