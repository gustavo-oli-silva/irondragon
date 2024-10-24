package br.unitins.tp1.irondragon.service;

import br.unitins.tp1.irondragon.dto.request.PedidoRequestDTO;
import br.unitins.tp1.irondragon.model.pedido.Pedido;

import java.util.List;

public interface PedidoService {
    public Pedido findById(Long id);

    public List<Pedido> findByUsername(String username);

    public Pedido create(PedidoRequestDTO dto);

    public void delete(Long id);
}
