package br.unitins.tp1.irondragon.service;

import br.unitins.tp1.irondragon.dto.request.ClienteRequestDTO;
import br.unitins.tp1.irondragon.model.Cliente;
import br.unitins.tp1.irondragon.repository.ClienteRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
public class ClienteServiceImpl implements ClienteService {
    @Inject
    public ClienteRepository clienteRepository;

    @Override
    public Cliente findById(Long id) {
        return clienteRepository.findById(id);
    }

    @Override
    public List<Cliente> findByNome(String nome) {
        return List.of();
    }

    @Override
    public List<Cliente> findAll() {
        return clienteRepository.findAll().list();
    }

    @Transactional
    @Override
    public Cliente create(ClienteRequestDTO dto) {
        Cliente cliente = new Cliente();
        cliente.setNome(dto.nome());
        cliente.setEmail(dto.email());
        cliente.setCpf(dto.cpf());
        cliente.setSenha(dto.senha());

        clienteRepository.persist(cliente);

        return cliente;
    }

    @Transactional
    @Override
    public void update(Long id, ClienteRequestDTO dto) {
        Cliente cliente = clienteRepository.findById(id);

        cliente.setNome(dto.nome());
        cliente.setEmail(dto.email());
        cliente.setCpf(dto.cpf());
        cliente.setSenha(dto.senha());
    }

    @Transactional
    @Override
    public void delete(Long id) {
        clienteRepository.deleteById(id);
    }
}
