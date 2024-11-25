package br.unitins.tp1.irondragon.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class TelefoneFornecedor extends DefaultEntity {
    @Column(nullable = false)
    private String codigoArea;
    @Column(nullable = false)
    private String numero;

}
