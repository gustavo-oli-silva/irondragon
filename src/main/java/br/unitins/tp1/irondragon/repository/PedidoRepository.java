package br.unitins.tp1.irondragon.repository;

import br.unitins.tp1.irondragon.model.pedido.Pedido;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class PedidoRepository implements PanacheRepository<Pedido> {
    public List<Pedido> findPedidoWherePagamentoIsNull() {
        return find("SELECT p FROM Pedido p WHERE p.pagamento IS NULL").list();
    }

    public Pedido findPedidoByIdPagamento(Long idPedido) {
        return find("SELECT p FROM Pedido p WHERE p.pagamento.id = ?1", idPedido).firstResult();
    }

    public List<Pedido> listPedidoByUsername(String username) {
        return find("SELECT p FROM Pedido p WHERE p.cliente.usuario.username = ?1", username).list();
    }
}
