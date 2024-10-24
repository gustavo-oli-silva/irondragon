package br.unitins.tp1.irondragon.service;

import br.unitins.tp1.irondragon.dto.request.ClienteRequestDTO;
import br.unitins.tp1.irondragon.model.Usuario;
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
    public Usuario findById(Long id) {
        return clienteRepository.findById(id);
    }

    @Override
    public List<Usuario> findByNome(String nome) {
        return clienteRepository.findByNome(nome);
    }

    @Override
    public List<Usuario> findAll() {
        return clienteRepository.findAll().list();
    }

    @Transactional
    @Override
    public Usuario create(ClienteRequestDTO dto) {
        Usuario usuario = new Usuario();
        usuario.setNome(dto.nome());
        usuario.setEmail(dto.email());
        usuario.setCpf(dto.cpf());
        usuario.setSenha(dto.senha());

        clienteRepository.persist(usuario);

        return usuario;
    }

    @Transactional
    @Override
    public void update(Long id, ClienteRequestDTO dto) {
        Usuario usuario = clienteRepository.findById(id);

        usuario.setNome(dto.nome());
        usuario.setEmail(dto.email());
        usuario.setCpf(dto.cpf());
        usuario.setSenha(dto.senha());
    }

    @Transactional
    @Override
    public void delete(Long id) {
        clienteRepository.deleteById(id);
    }
}
