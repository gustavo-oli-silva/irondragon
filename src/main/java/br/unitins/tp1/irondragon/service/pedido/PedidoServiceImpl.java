package br.unitins.tp1.irondragon.service.pedido;

import br.unitins.tp1.irondragon.dto.request.ItemPedidoRequestDTO;
import br.unitins.tp1.irondragon.dto.request.PedidoRequestDTO;
import br.unitins.tp1.irondragon.model.pedido.ItemPedido;
import br.unitins.tp1.irondragon.model.pedido.Pedido;
import br.unitins.tp1.irondragon.repository.PedidoRepository;
import br.unitins.tp1.irondragon.service.lote.LoteService;
import br.unitins.tp1.irondragon.service.usuario.UsuarioService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class PedidoServiceImpl implements PedidoService {
    @Inject
    public PedidoRepository pedidoRepository;

    @Inject
    public UsuarioService usuarioService;

    @Inject
    public LoteService loteService;

    @Override
    public Pedido findById(Long id) {
        return pedidoRepository.findById(id);
    }

    public Pedido findByUsername(String username) {
        return pedidoRepository.findByUsername(username);
    }

    public List<Pedido> findAll() {
        return pedidoRepository.findAll().list();
    }

    @Transactional
    @Override
    public Pedido create(PedidoRequestDTO dto, String username) {
        Pedido pedido = new Pedido();
        pedido.setData(LocalDateTime.now());
        pedido.setUsuario(usuarioService.findByUsername(username));
        pedido.setListaItemPedido(new ArrayList<>());
        pedido.setEnderecoEntrega(dto.endereco().toEntityEnderecoEntrega());

        for(ItemPedidoRequestDTO item: dto.listaItemPedido()) {
            ItemPedido itemPedido = new ItemPedido();
            itemPedido.setLote(loteService.findByIdProcessador(item.idProcessador()));
            itemPedido.setQuantidade(item.quantidade());
            itemPedido.setPreco(item.preco());

            pedido.getListaItemPedido().add(itemPedido);
        }

        pedido.setValorTotal(calcularValorTotal(pedido.getListaItemPedido()));

        pedidoRepository.persist(pedido);

        return pedido;
    }

    public Double calcularValorTotal(List<ItemPedido> listaDePedidos) {
        if(listaDePedidos == null || listaDePedidos.isEmpty()) {
            return 0.0;
        }

        double valor = 0.0;

        for(ItemPedido itemPedido: listaDePedidos) {
            valor += itemPedido.getPreco() * itemPedido.getQuantidade();
        }

        return valor;
    }
}
