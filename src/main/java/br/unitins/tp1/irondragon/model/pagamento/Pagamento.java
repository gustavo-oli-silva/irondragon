package br.unitins.tp1.irondragon.model.pagamento;

import br.unitins.tp1.irondragon.model.DefaultEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Pagamento extends DefaultEntity {
    private Double valor;
    private Boolean pago;
}
