package br.unitins.tp1.irondragon.model.processador;

import br.unitins.tp1.irondragon.model.DefaultEntity;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Conectividade extends DefaultEntity {
    private Float pci;
    private String tipoMemoria;
    private Integer canaisMemoria;
}

