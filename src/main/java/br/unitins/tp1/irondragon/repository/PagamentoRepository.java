package br.unitins.tp1.irondragon.repository;

import br.unitins.tp1.irondragon.model.pagamento.Boleto;
import br.unitins.tp1.irondragon.model.pagamento.Pagamento;
import br.unitins.tp1.irondragon.model.pagamento.Pix;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class PagamentoRepository implements PanacheRepository<Pagamento> {
    public Pix getPixByPagamentoId(Long id) {
        return find("SELECT p FROM Pix p WHERE p.id = ?1", id).firstResult();
    }

    public Boleto getBoletoByPagamentoId(Long id) {
        return find("SELECT b FROM Boleto b WHERE b.id = ?1", id).firstResult();
    }

    public List<Boleto> getListOfBoleto() {
        return find("SELECT b FROM Boleto b").list();
    }

    public List<Pix> getListOfPix() {
        return find("SELECT p FROM Pix p").list();
    }
}
