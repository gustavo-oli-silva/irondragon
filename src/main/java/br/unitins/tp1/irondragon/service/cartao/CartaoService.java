package br.unitins.tp1.irondragon.service.cartao;

import br.unitins.tp1.irondragon.dto.request.CartaoRequestDTO;
import br.unitins.tp1.irondragon.model.Cartao;

import java.util.List;

public interface CartaoService {
    public Cartao findById(Long id);

    public List<Cartao> findByNome(String nome);

    public List<Cartao> findAll();

    public Cartao create(CartaoRequestDTO dto, String username);

    public void update(Long id, CartaoRequestDTO dto, String username);

    List<Cartao> listByUsername(String username);

    public void delete(Long idCartao, String username);
}
