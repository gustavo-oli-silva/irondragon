package br.unitins.tp1.irondragon.service.endereco;

import br.unitins.tp1.irondragon.dto.request.EnderecoRequestDTO;
import br.unitins.tp1.irondragon.model.Cartao;
import br.unitins.tp1.irondragon.model.Endereco;
import br.unitins.tp1.irondragon.model.usuario.Usuario;
import br.unitins.tp1.irondragon.repository.EnderecoRepository;
import br.unitins.tp1.irondragon.service.cidade.CidadeService;
import br.unitins.tp1.irondragon.service.usuario.UsuarioService;
import br.unitins.tp1.irondragon.validation.ValidationException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
public class EnderecoServiceImpl implements EnderecoService {
    @Inject
    public EnderecoRepository enderecoRepository;

    @Inject
    public UsuarioService usuarioService;

    @Inject
    public CidadeService cidadeService;

    @Override
    public Endereco findById(Long id) {
        return enderecoRepository.findById(id);
    }

    @Override
    public Endereco findByIdAndUsername(Long id, String username) {
        Usuario usuario = usuarioService.findByUsername(username);
        Endereco endereco = enderecoRepository.findById(id);

        if(endereco == null) {
            throw new ValidationException("endereco", "Endereço informado não existe!");
        }

        validarUsuarioEndereco(usuario, endereco);

        return endereco;
    }

    @Override
    public List<Endereco> findAll() {
        return enderecoRepository.findAll().list();
    }

    @Override
    public List<Endereco> listByUsername(String username) {
        Usuario usuario = usuarioService.findByUsername(username);

        return usuario.getEnderecos();
    }

    @Transactional
    @Override
    public Endereco create(EnderecoRequestDTO dto, String username) {
        Usuario usuario = usuarioService.findByUsername(username);

        Endereco endereco = dto.toEntityEndereco();
        endereco.setCidade(cidadeService.findById(dto.idCidade()));

        usuario.getEnderecos().add(endereco);

        return endereco;
    }

    @Transactional
    @Override
    public void update(Long id, EnderecoRequestDTO dto, String username) {
        Usuario usuario = usuarioService.findByUsername(username);

        Endereco endereco = enderecoRepository.findById(id);

        if(endereco == null) {
            throw new ValidationException("endereco", "Endereço informado não existe!");
        }

        validarUsuarioEndereco(usuario, endereco);

        endereco.setNumero(dto.numero());
        endereco.setComplemento((dto.complemento() == null) ? "Sem complemento" : dto.complemento());
        endereco.setBairro(dto.bairro());
        endereco.setLogradouro(dto.logradouro());
        endereco.setCidade(cidadeService.findById(dto.idCidade()));
        endereco.setCep(dto.cep());
    }

    @Transactional
    @Override
    public void delete(Long id, String username) {
        Usuario usuario = usuarioService.findByUsername(username);

        Endereco endereco = enderecoRepository.findById(id);

        if(endereco == null) {
            throw new ValidationException("endereco", "Endereço informado não existe!");
        }

        validarUsuarioEndereco(usuario, endereco);

        usuario.getEnderecos().remove(endereco);
    }

    public void validarUsuarioEndereco(Usuario usuario, Endereco endereco) {
        for(Endereco e: usuario.getEnderecos()) {
            if(e.equals(endereco)) {
                return;
            }
        }

        throw new ValidationException("endereco", "O endereco informado é inválido!");
    }
}
