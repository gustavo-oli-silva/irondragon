package br.unitins.tp1.irondragon.model;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
