package br.unitins.tp1.irondragon.model;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class TelefoneFornecedor extends DefaultEntity {
    private String codigoArea;
    private String numero;

}
