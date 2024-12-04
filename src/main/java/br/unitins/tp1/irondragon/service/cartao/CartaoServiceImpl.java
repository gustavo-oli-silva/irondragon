package br.unitins.tp1.irondragon.service.cartao;

import br.unitins.tp1.irondragon.dto.request.CartaoRequestDTO;
import br.unitins.tp1.irondragon.model.Cartao;
import br.unitins.tp1.irondragon.model.TipoCartao;
import br.unitins.tp1.irondragon.model.usuario.Cliente;
import br.unitins.tp1.irondragon.model.usuario.Usuario;
import br.unitins.tp1.irondragon.repository.CartaoRepository;
import br.unitins.tp1.irondragon.service.cliente.ClienteService;
import br.unitins.tp1.irondragon.service.usuario.UsuarioService;
import br.unitins.tp1.irondragon.validation.ValidationException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
public class CartaoServiceImpl implements CartaoService {
    @Inject
    public CartaoRepository cartaoRepository;

    @Inject
    public ClienteService clienteService;

    @Override
    public Cartao findById(Long id) {
        Cartao cartao = cartaoRepository.findById(id);

        if(cartao == null) {
            throw new ValidationException("cartao", "Cartao informado não existe!");
        }

        return cartaoRepository.findById(id);
    }

    @Override
    public List<Cartao> findByNome(String nome) {
        return cartaoRepository.findByNome(nome);
    }

    @Override
    public List<Cartao> findAll() {
        return cartaoRepository.findAll().list();
    }

    @Transactional
    @Override
    public Cartao create(CartaoRequestDTO dto, String username) {
        Cartao cartao = new Cartao();
        Cliente cliente = clienteService.findByUsername(username);

        cartao.setNumero(dto.numero());
        cartao.setNomeTitular(dto.nomeTitular());
        cartao.setCpf(dto.cpf());
        cartao.setCvc(dto.cvc());
        cartao.setValidade(dto.validade());
        cartao.setTipo(TipoCartao.valueOf(dto.tipoCartao()));

        cliente.getListaDeCartoes().add(cartao);

        return cartao;
    }

    @Transactional
    @Override
    public void update(Long id, CartaoRequestDTO dto, String username) {
        Cartao cartao = cartaoRepository.findById(id);
        Cliente cliente = clienteService.findByUsername(username);

        validarClienteCartao(cartao, cliente);

        cartao.setNumero(dto.numero());
        cartao.setNomeTitular(dto.nomeTitular());
        cartao.setCpf(dto.cpf());
        cartao.setCvc(dto.cvc());
        cartao.setValidade(dto.validade());
        cartao.setTipo(TipoCartao.valueOf(dto.tipoCartao()));
    }

    public List<Cartao> listByUsername(String username) {
        Cliente cliente = clienteService.findByUsername(username);
        return cliente.getListaDeCartoes();
    }

    @Transactional
    @Override
    public void delete(Long idCartao, String username) {
        Cartao cartao = cartaoRepository.findById(idCartao);
        Cliente cliente = clienteService.findByUsername(username);

        validarClienteCartao(cartao, cliente);

        cliente.getListaDeCartoes().remove(cartao);
    }

    public void validarClienteCartao(Cartao cartao, Cliente cliente) {
        for(Cartao c: cliente.getListaDeCartoes()) {
            if(c.equals(cartao)) {
                return;
            }
        }

        throw new ValidationException("cartao", "O cartão informado é inválido!");
    }
}
