package br.unitins.tp1.irondragon.service.pedido;

import br.unitins.tp1.irondragon.dto.request.ItemPedidoRequestDTO;
import br.unitins.tp1.irondragon.dto.request.PedidoRequestDTO;
import br.unitins.tp1.irondragon.model.Endereco;
import br.unitins.tp1.irondragon.model.pedido.*;
import br.unitins.tp1.irondragon.model.usuario.Cliente;
import br.unitins.tp1.irondragon.repository.PedidoRepository;
import br.unitins.tp1.irondragon.service.cliente.ClienteService;
import br.unitins.tp1.irondragon.service.endereco.EnderecoService;
import br.unitins.tp1.irondragon.service.lote.LoteService;
import br.unitins.tp1.irondragon.service.processador.ProcessadorService;
import br.unitins.tp1.irondragon.validation.ValidationException;
import io.quarkus.scheduler.Scheduled;
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
    public ClienteService clienteService;

    @Inject
    public EnderecoService enderecoService;

    @Inject
    public LoteService loteService;

    @Inject
    public ProcessadorService processadorService;

    @Override
    public Pedido findById(Long id) {
        Pedido pedido = pedidoRepository.findById(id);

        if(pedido == null) {
            throw new ValidationException("id", "Pedido informado não existe!");
        }

        return pedido;
    }

    @Override
    public Pedido findByUsername(Long id, String username) {
        Cliente cliente = clienteService.findByUsername(username);
        Pedido pedido = pedidoRepository.findById(id);

        if(pedido == null) {
            throw new ValidationException("id", "Pedido inválido!");
        }

        validarPedidoCliente(pedido, cliente);

        return pedido;
    }

    public List<Pedido> findAll() {
        return pedidoRepository.findAll().list();
    }

    @Transactional
    @Override
    public Pedido create(PedidoRequestDTO dto, String username) {
        Pedido pedido = new Pedido();
        Endereco endereco = enderecoService.findByIdAndUsername(dto.idEndereco(), username);

        EnderecoEntrega enderecoEntrega = new EnderecoEntrega();

        enderecoEntrega.setLogradouro(endereco.getLogradouro());
        enderecoEntrega.setNumero(endereco.getNumero());
        enderecoEntrega.setCidade(endereco.getCidade());
        enderecoEntrega.setCep(endereco.getCep());
        enderecoEntrega.setComplemento(endereco.getComplemento());
        enderecoEntrega.setBairro(endereco.getBairro());
        enderecoEntrega.setCidade(enderecoEntrega.getCidade());

        pedido.setData(LocalDateTime.now());
        pedido.setCliente(clienteService.findByUsername(username));
        pedido.setListaItemPedido(new ArrayList<>());
        pedido.setEnderecoEntrega(enderecoEntrega);
        pedido.setStatusPedido(StatusPedido.PAGAMENTO_PENDENTE);

        for(ItemPedidoRequestDTO item: dto.listaItemPedido()) {
            if(item.quantidade() <= 0) {
                throw new ValidationException("quantidade", "Quantidade não deve ser igual ou inferior a 0");
            }

            Lote lote = loteService.findByIdProcessador(item.idProcessador());

            if(item.quantidade() > loteService.findEstoqueByIdProcessador(item.idProcessador())) {
                throw new ValidationException("quantidade", "A quantidade do pedido é superior ao disponível no estoque!");
            }

            int quantidade = item.quantidade();

            while(quantidade > 0) {
                ItemPedido itemPedido = new ItemPedido();
                if(lote.getEstoque() == 0) {
                    lote = loteService.findByIdProcessador(item.idProcessador());
                }

                itemPedido.setLote(lote);

                int qntRetirada = Math.min(quantidade, lote.getEstoque());

                lote.setEstoque(lote.getEstoque() - qntRetirada);

                quantidade -= qntRetirada;

                itemPedido.setQuantidade(qntRetirada);
                itemPedido.setPreco(lote.getProcessador().getPreco());
                pedido.getListaItemPedido().add(itemPedido);
            }
        }

        pedido.setValorTotal(calcularValorTotal(pedido.getListaItemPedido()));

        pedidoRepository.persist(pedido);

        return pedido;
    }

    @Transactional
    @Scheduled(every = "1m")
    public void verifyIfPaymentIsNull() {
        List<Pedido> pedido = pedidoRepository.findPedidoWherePagamentoIsNull();

        pedido.forEach(p -> {
            if(LocalDateTime.now().isAfter(p.getData().plusMinutes(5))) {
                returnToLote(p);
                pedidoRepository.delete(p);
            }
        });
    }

    @Transactional
    @Override
    public void cancelPedido(Long idPedido, String username) {
        Pedido pedido = pedidoRepository.findById(idPedido);

        if(pedido == null) throw new ValidationException("pedido", "O pedido não existe");

        Cliente cliente = clienteService.findByUsername(username);

        validarPedidoCliente(pedido, cliente);

        if(pedido.getStatusPedido() == StatusPedido.PREPARANDO_PRODUTO) {
            pedido.setStatusPedido(StatusPedido.PEDIDO_CANCELADO);
            returnToLote(pedido);
        } else {
            throw new ValidationException("statusPedido", "O pedido não pode ser cancelado");
        }
    }

    @Override
    public Pedido findPedidoByIdPagamento(Long idPagamento) {
        Pedido pedido = pedidoRepository.findPedidoByIdPagamento(idPagamento);

        if(pedido == null) {
            throw new ValidationException("id", "Pedido informado não existe!");
        }

        return pedido;
    }

    @Transactional
    @Override
    public void updateStatusPedido(Long idPedido, StatusPedido statusPedido) {
        Pedido pedido = pedidoRepository.findById(idPedido);

        if(pedido == null) {
            throw new ValidationException("id", "O pedido não existe!");
        }

        pedido.setStatusPedido(statusPedido);
    }

    @Override
    public List<Pedido> listByUsername(String username) {
        return pedidoRepository.listPedidoByUsername(username);
    }

    public void returnToLote(Pedido pedido) {
        for(ItemPedido itemPedido: pedido.getListaItemPedido()) {
            Lote lote = itemPedido.getLote();

            lote.setEstoque(lote.getEstoque() + itemPedido.getQuantidade());
        }
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

    public void validarPedidoCliente(Pedido pedido, Cliente cliente) {
        if(pedido.getCliente().equals(cliente)) return;

        throw new ValidationException("pedido", "Pedido inválido!");
    }
}
