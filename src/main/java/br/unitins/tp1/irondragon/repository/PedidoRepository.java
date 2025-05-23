package br.unitins.tp1.irondragon.repository;

import br.unitins.tp1.irondragon.model.pedido.Pedido;
import br.unitins.tp1.irondragon.model.pedido.StatusPedido;
import br.unitins.tp1.irondragon.model.processador.Processador;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.TypedQuery;

import java.util.Arrays;
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

     public List<Processador> findProcessadoresMaisVendidos() {
        List<StatusPedido> statusExcluidos = Arrays.asList(
            StatusPedido.PEDIDO_EXPIRADO,
            StatusPedido.PEDIDO_CANCELADO
        );

        String jpql = """
            SELECT pro
            FROM Pedido p
            JOIN p.listaItemPedido ip
            JOIN ip.lote l
            JOIN l.processador pro
            WHERE p.statusPedido NOT IN (:statusExcluidos)
            GROUP BY pro
            ORDER BY SUM(ip.quantidade) DESC
            """;

        TypedQuery<Processador> query = getEntityManager().createQuery(jpql, Processador.class);
        query.setParameter("statusExcluidos", statusExcluidos);
        query.setMaxResults(10);

        return query.getResultList();
    }
}
