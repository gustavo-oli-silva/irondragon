package br.unitins.tp1.irondragon.model.pagamento;

import br.unitins.tp1.irondragon.model.DefaultEntity;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Pagamento extends DefaultEntity {
    private Double valor;
}
