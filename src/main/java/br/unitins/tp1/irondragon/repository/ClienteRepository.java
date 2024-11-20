package br.unitins.tp1.irondragon.repository;

import br.unitins.tp1.irondragon.model.usuario.Cliente;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ClienteRepository implements PanacheRepository<Cliente> {
    public Cliente findClienteByIdUsuario(Long idUsuario) {
        StringBuffer jpql = new StringBuffer();

        jpql.append("SELECT c FROM Cliente ")
                .append("WHERE c.usuario = ?1");

        return find(jpql.toString(), idUsuario).firstResult();
    }
}
