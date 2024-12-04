package br.unitins.tp1.irondragon.service.pagamento;

import br.unitins.tp1.irondragon.model.Cartao;
import br.unitins.tp1.irondragon.model.pagamento.Boleto;
import br.unitins.tp1.irondragon.model.pagamento.CartaoPagamento;
import br.unitins.tp1.irondragon.model.pagamento.Pagamento;
import br.unitins.tp1.irondragon.model.pagamento.Pix;
import br.unitins.tp1.irondragon.model.pedido.Pedido;
import br.unitins.tp1.irondragon.model.pedido.StatusPedido;
import br.unitins.tp1.irondragon.model.usuario.Cliente;
import br.unitins.tp1.irondragon.repository.PagamentoRepository;
import br.unitins.tp1.irondragon.service.cartao.CartaoService;
import br.unitins.tp1.irondragon.service.cliente.ClienteService;
import br.unitins.tp1.irondragon.service.pedido.PedidoService;
import br.unitins.tp1.irondragon.validation.ValidationException;
import io.quarkus.scheduler.Scheduled;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.time.*;
import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class PagamentoServiceImpl implements PagamentoService {
    @Inject
    public PagamentoRepository pagamentoRepository;

    @Inject
    public PedidoService pedidoService;

    @Inject
    public ClienteService clienteService;

    @Inject
    public CartaoService cartaoService;

    @Override
    public Pagamento findById(Long id) {
        return pagamentoRepository.findById(id);
    }

    @Override
    public Pagamento findByIdPedido(Long idPedido) {
        Pedido pedido = pedidoService.findById(idPedido);

        if(pedido == null) {
            throw new ValidationException("pedido", "Pedido não existe!");
        }

        return pedido.getPagamento();
    }

    @Transactional
    @Override
    public Pix generatePix(Long idPedido, String username) {
        Pedido pedido = pedidoService.findById(idPedido);

        if(pedido == null) {
            throw new ValidationException("pedido", "Pedido não existe!");
        }

        validarPedidoCliente(pedido, username);

        Instant validade = Instant.now().plus(Duration.ofHours(2));

        Pix pix = new Pix();

        pix.setChave(UUID.randomUUID().toString());
        pix.setDataValidade(LocalDateTime.ofInstant(validade, ZoneId.systemDefault()));
        pix.setDestinatario("Iron Dragon LTDA");
        pix.setValor(pedido.getValorTotal());
        pix.setPago(false);

        pedido.setPagamento(pix);

        return pix;
    }


    @Transactional
    @Override
    public void payment(Long idPedido, Long idPagamento, String username, String tipoPagamento) {
        Pedido pedido = pedidoService.findById(idPedido);
        Pagamento pagamento = pagamentoRepository.findById(idPagamento);

        if(pagamento == null) {
            throw new ValidationException("pagamento", "Cadastro de pagamento não encontrado!");
        }

        Cliente cliente = clienteService.findByUsername(username);

        if(!(pedido.getCliente().equals(cliente))) {
            throw new ValidationException("pedido", "Pedido inválido!");
        }

        if(!(pedido.getPagamento().equals(pagamento))) {
            throw new ValidationException("pedido", "Pedido inválido!");
        }

        if(pagamento.getPago()) {
            throw new ValidationException("pagamento", "Pagamento já foi realizado!");
        }

        switch (tipoPagamento) {
            case "boleto" -> {
                Boleto boleto = pagamentoRepository.getBoletoByPagamentoId(idPagamento);

                if(LocalDate.now().isAfter(boleto.getDataValidade())) {
                    throw new ValidationException("pagamento", "Pagamento vencido!");
                }

                boleto.setPago(true);
                pedido.setStatusPedido(StatusPedido.PREPARANDO_PRODUTO);
            }

            case "pix" -> {
                Pix pix = pagamentoRepository.getPixByPagamentoId(idPagamento);

                if(LocalDateTime.now().isAfter(pix.getDataValidade())) {
                    throw new ValidationException("pagamento", "Pagamento vencido!");
                }

                pix.setPago(true);
                pedido.setStatusPedido(StatusPedido.PREPARANDO_PRODUTO);
            }
        }
    }

    @Transactional
    @Scheduled(every = "1h")
    public void verifyIfBoletoIsExpired() {
        List<Boleto> boletos = pagamentoRepository.getListOfBoleto();

        boletos.forEach(b -> {
            if(LocalDate.now().isAfter(b.getDataValidade())) {
                Pedido pedido = pedidoService.findPedidoByIdPagamento(b.getId());
                pedido.setStatusPedido(StatusPedido.PEDIDO_EXPIRADO);
            }
        });
    }

    @Transactional
    @Scheduled(every = "1h")
    public void verifyIfPixIsExpired() {
        List<Pix> pix = pagamentoRepository.getListOfPix();

        pix.forEach(p -> {
            if(LocalDateTime.now().isAfter(p.getDataValidade())) {
                Pedido pedido = pedidoService.findPedidoByIdPagamento(p.getId());
                pedido.setStatusPedido(StatusPedido.PEDIDO_EXPIRADO);
            }
        });
    }

    @Transactional
    @Override
    public CartaoPagamento cardPayment(Long idPedido, long idCartao, String username) {
        Pedido pedido = pedidoService.findById(idPedido);

        if(pedido == null) {
            throw new ValidationException("pedido", "Pedido não existe!");
        }

        validarPedidoCliente(pedido, username);

        Cartao cartao = cartaoService.findById(idCartao);

        CartaoPagamento cartaoPagamento = new CartaoPagamento();

        cartaoPagamento.setCpf(cartao.getCpf());
        cartaoPagamento.setCvc(cartao.getCvc());
        cartaoPagamento.setValidade(cartao.getValidade());
        cartaoPagamento.setNumero(cartao.getNumero());
        cartaoPagamento.setNomeTitular(cartao.getNomeTitular());
        cartaoPagamento.setValor(pedido.getValorTotal());
        cartaoPagamento.setTipo(cartao.getTipo());
        cartaoPagamento.setPago(true);

        pedido.setStatusPedido(StatusPedido.PREPARANDO_PRODUTO);
        pedido.setPagamento(cartaoPagamento);

        return cartaoPagamento;
    }

    @Transactional
    @Override
    public Boleto generateBoleto(Long idPedido, String username) {
        Pedido pedido = pedidoService.findById(idPedido);

        if(pedido == null) {
            throw new ValidationException("pedido", "Pedido não existe!");
        }

        validarPedidoCliente(pedido, username);

        Boleto boleto = new Boleto();

        Instant validade = Instant.now().plus(Duration.ofDays(3));

        boleto.setCodigoBarras(UUID.randomUUID().toString());
        boleto.setValor(pedido.getValorTotal());
        boleto.setPago(false);
        boleto.setDataValidade(LocalDate.ofInstant(validade, ZoneId.systemDefault()));

        pedido.setPagamento(boleto);

        return boleto;
    }

    public void validarPedidoCliente(Pedido pedido, String username) {
        Cliente cliente = clienteService.findByUsername(username);

        if(!(cliente.equals(pedido.getCliente()))) {
            throw new ValidationException("cliente", "Pedido inválido!");
        }

        if(pedido.getPagamento() != null) {
            throw new ValidationException("pagamento", "Esse pedido já tem um pagamento!");
        }
    }
}
