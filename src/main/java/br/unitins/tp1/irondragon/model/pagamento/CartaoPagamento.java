package br.unitins.tp1.irondragon.model.pagamento;

import br.unitins.tp1.irondragon.model.TipoCartao;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
public class CartaoPagamento extends Pagamento {
    private String nomeTitular;
    private String numero;
    private String cpf;
    private LocalDate validade;
    private Integer cvc;
    private TipoCartao tipo;
}
