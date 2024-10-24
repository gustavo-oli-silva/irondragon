package br.unitins.tp1.irondragon.model;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Objects;

@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@Entity
public class Cartao extends DefaultEntity {
    private String nomeTitular;
    private String numero;
    private String cpf;
    private LocalDate validade;
    private Integer cvc;
    private TipoCartao tipo;
}
