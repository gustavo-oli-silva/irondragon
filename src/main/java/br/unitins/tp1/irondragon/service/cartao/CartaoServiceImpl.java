package br.unitins.tp1.irondragon.service.cartao;

import br.unitins.tp1.irondragon.dto.request.CartaoRequestDTO;
import br.unitins.tp1.irondragon.model.Cartao;
import br.unitins.tp1.irondragon.model.TipoCartao;
import br.unitins.tp1.irondragon.repository.CartaoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
public class CartaoServiceImpl implements CartaoService {
    @Inject
    public CartaoRepository cartaoRepository;

    @Override
    public Cartao findById(Long id) {
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
    public Cartao create(Long idCliente, CartaoRequestDTO dto) {
        Cartao cartao = new Cartao();

        cartao.setNumero(dto.numero());
        cartao.setNomeTitular(dto.nomeTitular());
        cartao.setCpf(dto.cpf());
        cartao.setCvc(dto.cvc());
        cartao.setValidade(dto.validade());
        cartao.setTipo(TipoCartao.valueOf(dto.tipoCartao()));

        cartaoRepository.persist(cartao);


        return cartao;
    }

    @Transactional
    @Override
    public void update(Long id, CartaoRequestDTO dto) {
        Cartao cartao = cartaoRepository.findById(id);

        cartao.setNumero(dto.numero());
        cartao.setNomeTitular(dto.nomeTitular());
        cartao.setCpf(dto.cpf());
        cartao.setCvc(dto.cvc());
        cartao.setValidade(dto.validade());
        cartao.setTipo(TipoCartao.valueOf(dto.tipoCartao()));
    }

    @Transactional
    @Override
    public void delete(Long idCartao) {
        cartaoRepository.deleteById(idCartao);
    }
}
