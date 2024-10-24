package br.unitins.tp1.irondragon.repository;

import br.unitins.tp1.irondragon.model.pedido.Pedido;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class PedidoRepository implements PanacheRepository<Pedido> {
    public List<Pedido> findByIdUsuario(Long idUsuario) {
        return find("SELECT p FROM Pedido p WHERE p.usuario.id = ?1", idUsuario).list();
    }
}
