package br.unitins.tp1.irondragon.model.processador;

import br.unitins.tp1.irondragon.model.DefaultEntity;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class ConsumoEnergetico extends DefaultEntity {
    private Integer energiaBasica;
    private Integer energiaMaxima;
}
