package br.unitins.tp1.irondragon.service;

import br.unitins.tp1.irondragon.dto.request.CartaoRequestDTO;
import br.unitins.tp1.irondragon.model.Cartao;
import br.unitins.tp1.irondragon.repository.CartaoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

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
        return List.of();
    }

    @Override
    public List<Cartao> findAll() {
        return cartaoRepository.findAll().list();
    }

    @Override
    public Cartao create(CartaoRequestDTO dto) {
        Cartao cartao = new Cartao();

        cartao.setNumero(dto.numero());
        cartao.setNomeTitular(dto.nomeTitular());
        cartao.setCpf(dto.cpf());
        cartao.setCvc(dto.cvc());

        cartaoRepository.persist(cartao);

        return cartao;
    }

    @Override
    public void update(Long id, CartaoRequestDTO dto) {
        Cartao cartao = cartaoRepository.findById(id);

        cartao.setNumero(dto.numero());
        cartao.setNomeTitular(dto.nomeTitular());
        cartao.setCpf(dto.cpf());
        cartao.setCvc(dto.cvc());
    }

    @Override
    public void delete(Long id) {
        cartaoRepository.deleteById(id);
    }
}
