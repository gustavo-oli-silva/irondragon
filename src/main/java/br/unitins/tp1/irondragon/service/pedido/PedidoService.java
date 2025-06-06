package br.unitins.tp1.irondragon.service.pedido;

import br.unitins.tp1.irondragon.dto.request.PedidoRequestDTO;
import br.unitins.tp1.irondragon.model.pedido.Pedido;
import br.unitins.tp1.irondragon.model.pedido.StatusPedido;
import br.unitins.tp1.irondragon.model.processador.Processador;

import java.util.List;

public interface PedidoService {
    public Pedido findById(Long id);

    List<Processador> findProcessadoresMaisVendidos();

    public Pedido create(PedidoRequestDTO dto, String username);

    void cancelPedido(Long idPedido, String username);

    Pedido findByUsername(Long id, String username);
    
    List<Pedido> findAll(int page, int pageSize);

    Long count();
    
    void updateStatusPedido(Long idPedido, StatusPedido statusPedido);
    
    List<Pedido> listByUsername(String username, Integer page, Integer pageSize);
    Long countByUsername(String username);

    Pedido findPedidoByIdPagamento(Long id);
}
