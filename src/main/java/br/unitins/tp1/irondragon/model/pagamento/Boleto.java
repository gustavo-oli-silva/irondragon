package br.unitins.tp1.irondragon.model.pagamento;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
public class Boleto extends Pagamento {
    private String codigoBarras;
    private LocalDate dataValidade;
}
