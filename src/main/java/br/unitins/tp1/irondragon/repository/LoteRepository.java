package br.unitins.tp1.irondragon.repository;

import java.util.List;

import br.unitins.tp1.irondragon.model.pedido.Lote;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class LoteRepository implements PanacheRepository<Lote> {
    /**
     *
     * @param idProcessador
     * @return retorna o processador com o lote mais antigo e com quantidade maior que 0
     */
    public Lote findByIdProcessador(Long idProcessador) {
        StringBuffer jpql = new StringBuffer();
        jpql.append("SELECT l FROM Lote l ")
                .append("WHERE l.processador.id = ?1 ")
                .append("AND l.estoque > 0 ")
                .append("ORDER BY l.data ASC");

        return find(jpql.toString(), idProcessador).firstResult();
    }

    /**
     *
     * @param codigo
     * @return retorna o lote pelo código
     */
    public Lote findByIdCodigo(String codigo) {
        StringBuffer jpql = new StringBuffer();
        jpql.append("SELECT l FROM Lote l ")
                .append("WHERE l.codigo = ?1");

        return find(jpql.toString(), codigo).firstResult();
    }

    public Integer findEstoqueByIdProcessador(Long id) {
        return find("SELECT l FROM Lote l WHERE l.processador.id = ?1", id)
                .stream()
                .map(Lote::getEstoque).reduce(Integer::sum)
                .orElse(0);
    }
    

    @SuppressWarnings("unchecked")
    public List<Lote> findLastLotesByProcessadorDistinctOn() {
    StringBuilder sql = new StringBuilder();
    sql.append("SELECT DISTINCT ON (lote.id_processador) * ");
    sql.append("FROM public.lote ");
    sql.append("ORDER BY lote.id_processador, lote.data DESC ");
    sql.append("LIMIT 12");

    return (List<Lote>) getEntityManager()
            .createNativeQuery(sql.toString(), Lote.class)
            .getResultList();
}

}

