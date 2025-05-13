package br.unitins.tp1.irondragon.service.usuario;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import br.unitins.tp1.irondragon.dto.request.keycloak.CredentialDTO;
import br.unitins.tp1.irondragon.dto.request.keycloak.KeycloakUserRequestDTO;
import br.unitins.tp1.irondragon.dto.request.usuario.EmailUpdateDTO;
import br.unitins.tp1.irondragon.dto.request.usuario.SenhaUpdateDTO;
import br.unitins.tp1.irondragon.dto.request.usuario.UsuarioRequestDTO;
import br.unitins.tp1.irondragon.model.usuario.Cliente;
import br.unitins.tp1.irondragon.model.usuario.Perfil;
import br.unitins.tp1.irondragon.model.usuario.Usuario;
import br.unitins.tp1.irondragon.repository.UsuarioRepository;
import br.unitins.tp1.irondragon.service.cliente.ClienteService;
import br.unitins.tp1.irondragon.service.hash.HashService;
import br.unitins.tp1.irondragon.service.keycloak.KeycloakAdminService;
import br.unitins.tp1.irondragon.validation.ValidationException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class UsuarioServiceImpl implements UsuarioService {
    @Inject
    public UsuarioRepository usuarioRepository;

    @Inject
    public ClienteService clienteService;

    @Inject
    public HashService hashService;

    @Inject
    public KeycloakAdminService keycloakAdminService;

    @Override
    public Usuario findById(Long id) {
        return usuarioRepository.findById(id);
    }

    @Override
    public Usuario findByUsername(String username) {
        return usuarioRepository.findByUsername(username);
    }

    @Override
    public Usuario findByUsernameAndSenha(String username, String senha) {
        return usuarioRepository.findByUsernameAndSenha(username, senha);
    }

    @Override
    public List<Usuario> findAll() {
        return usuarioRepository.findAll().list();
    }

    @Transactional
    @Override
    public Usuario create(UsuarioRequestDTO dto) {
        Usuario usuario = new Usuario();
        usuario.setNome(dto.nome());
        usuario.setUsername(dto.email());
        usuario.setEmail(dto.email());
        usuario.setDataCriacao(LocalDateTime.now());
        usuario.setCpf(dto.cpf());
        usuario.setPerfil(Perfil.USER);
        usuario.setSenha(hashService.getHashSenha(dto.senha()));
        
        // usuario.setEnderecos(new ArrayList<>());
        // usuario.setDataNascimento(dto.dataNascimento());
        // usuario.setTelefone(dto.telefone().toEntityTelefoneUsuario());

        usuarioRepository.persist(usuario);

        clienteService.create(usuario.getUsername());
        createKeycloakUser(dto);

        return usuario;
    }

    private void createKeycloakUser(UsuarioRequestDTO dto) {
        List<CredentialDTO> credentials = List.of(new CredentialDTO("password", dto.senha(), false));
        KeycloakUserRequestDTO keycloackUser = new KeycloakUserRequestDTO(dto.email(), dto.email(), dto.nome(), true, credentials);

        keycloakAdminService.createKeycloakUser(keycloackUser);
        String userId =  keycloakAdminService.getUserIdByUsername(dto.email());
        keycloakAdminService.assignRealmRoleToUser(userId, Perfil.USER.getLabel());
    }

    @Transactional
    @Override
    public void updateEmail(EmailUpdateDTO dto, String username) {
        Usuario usuario = usuarioRepository.findByUsername(username);

        Usuario email = usuarioRepository.findByEmail(dto.email());

        if(email != null) {
            throw new ValidationException("email", "Email inválido!");
        }

        usuario.setEmail(dto.email());
    }

    @Transactional
    @Override
    public void updateSenha(SenhaUpdateDTO dto, String username) {
        Usuario usuario = usuarioRepository.findByUsername(username);

        usuario.setSenha(hashService.getHashSenha(dto.senha()));
    }

    @Transactional
    @Override
    public void changeProfile(Usuario usuario, Perfil perfil) {
        usuario.setPerfil(perfil);
    }

    @Transactional
    @Override
    public Usuario updateNomeImagem(String username, String nomeImagem) {
        Usuario usuario = usuarioRepository.findByUsername(username);

        usuario.setNomeImagem(nomeImagem);

        return usuario;
    }

    @Transactional
    @Override
    public void delete(Long id) {
        Usuario usuario = usuarioRepository.findById(id);

        if(usuario == null) {
            throw new ValidationException("id", "Usuário não encontrado!");
        }

        Cliente cliente = clienteService.findByUsername(usuario.getUsername());

        if(cliente == null) {
            throw new ValidationException("username", "Cliente não foi encontrado para esse username");
        }

        clienteService.delete(cliente.getId());
        usuarioRepository.delete(usuario);
    }

    @Override
    public Usuario findByCpf(String cpf) {
        Usuario usuario = usuarioRepository.findByCpf(cpf);
        if(usuario == null){
            throw new ValidationException("cpf", "O CPF informado é inválido");
        }
        System.out.println(usuario.getUsername());

        return usuario;
    }
}
