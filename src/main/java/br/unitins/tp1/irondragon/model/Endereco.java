package br.unitins.tp1.irondragon.model;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Endereco extends DefaultEntity {
    private String logradouro;
    private String cep;
    private String bairro;
    private String complemento;
    private Integer numero;

    @ManyToOne
    @JoinColumn(name = "id_cidade")
    private Cidade cidade;
}
