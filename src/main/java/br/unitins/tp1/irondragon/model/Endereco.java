package br.unitins.tp1.irondragon.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Endereco extends DefaultEntity {
    @Column(nullable = false)
    private String logradouro;

    @Column(nullable = false)
    private String cep;

    @Column(nullable = false)
    private String bairro;

    private String complemento;

    @Column(nullable = false)
    private Integer numero;

    @ManyToOne
    @JoinColumn(name = "id_cidade")
    private Cidade cidade;
}
