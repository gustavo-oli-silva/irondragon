package br.unitins.tp1.irondragon.repository;

import br.unitins.tp1.irondragon.model.usuario.Cliente;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ClienteRepository implements PanacheRepository<Cliente> {
    public Cliente findClienteByIdUsuario(Long idUsuario) {
        StringBuffer jpql = new StringBuffer();

        jpql.append("SELECT c FROM Cliente c ")
                .append("WHERE c.usuario = ?1");

        return find(jpql.toString(), idUsuario).firstResult();
    }

    public Cliente findByUsername(String username) {
        return find("SELECT c FROM Cliente c WHERE c.usuario.username = ?1", username).firstResult();
    }

    public Cliente findByCpf(String cpf) {
        return find("SELECT c FROM Cliente c WHERE c.cpf = ?1", cpf).firstResult();
    }
}
