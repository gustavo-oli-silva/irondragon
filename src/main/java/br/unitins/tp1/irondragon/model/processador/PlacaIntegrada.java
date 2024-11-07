package br.unitins.tp1.irondragon.model.processador;

import br.unitins.tp1.irondragon.model.DefaultEntity;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class PlacaIntegrada extends DefaultEntity {
    private String nome;
    private Float directX;
    private Float openGl;
    private Float vulkan;
}
