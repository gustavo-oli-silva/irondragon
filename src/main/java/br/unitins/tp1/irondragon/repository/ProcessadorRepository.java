package br.unitins.tp1.irondragon.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.unitins.tp1.irondragon.dto.request.ProcessadorFilterRequest;
import br.unitins.tp1.irondragon.model.processador.Processador;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import net.bytebuddy.asm.Advice.Return;

@ApplicationScoped
public class ProcessadorRepository implements PanacheRepository<Processador> {

    public PanacheQuery<Processador> findByNome(String nome) {
        return find("SELECT p FROM Processador p WHERE p.nome like ?1", "%" + nome + "%");
    }

    public PanacheQuery<Processador> findByFiltros(ProcessadorFilterRequest filtros) {
        var jpql = new StringBuilder("FROM Processador p WHERE 1=1 ");
        Map<String, Object> params = new HashMap<>();

        if (filtros == null) {
            return find("FROM Processador p ORDER BY p.preco ASC");
        }

      
        if( filtros.nome() != null && !filtros.nome().isBlank()) {
            jpql.append("AND LOWER(p.nome) LIKE :nome ");
            params.put("nome", "%" + filtros.nome().toLowerCase() + "%");
        }

        if (filtros.fabricante() != null && !filtros.fabricante().isBlank()) {
            jpql.append("AND p.fabricante.nome = :fabricante ");
            params.put("fabricante", filtros.fabricante());
        }

        if (filtros.precoMin() != null && filtros.precoMax() != null) {
            jpql.append("AND p.preco BETWEEN :precoMin AND :precoMax ");
            params.put("precoMin", filtros.precoMin());
            params.put("precoMax", filtros.precoMax());
        } else {
            if (filtros.precoMin() != null) {
                jpql.append("AND p.preco >= :precoMin ");
                params.put("precoMin", filtros.precoMin());
            }

            if (filtros.precoMax() != null) {
                jpql.append("AND p.preco <= :precoMax ");
                params.put("precoMax", filtros.precoMax());
            }
        }

        if (filtros.sockets() != null && !filtros.sockets().isEmpty()) {
            jpql.append("AND p.socket IN :sockets ");
            params.put("sockets", filtros.sockets());
        }

        if (filtros.graficosIntegrados() != null
                && !filtros.graficosIntegrados().equalsIgnoreCase("todos")) {

            if (filtros.graficosIntegrados().equalsIgnoreCase("sim")) {
                jpql.append("AND p.placaIntegrada IS NOT NULL ");
            } else if (filtros.graficosIntegrados().equalsIgnoreCase("nao")) {
                jpql.append("AND p.placaIntegrada IS NULL ");
            }
        }

        // ordenação
        String orderClause = filtros.sortBy().equalsIgnoreCase("preco-desc")
                ? " ORDER BY p.preco DESC"
                : " ORDER BY p.preco ASC";
        jpql.append(orderClause);

        return find(jpql.toString(), params);
    }
}
