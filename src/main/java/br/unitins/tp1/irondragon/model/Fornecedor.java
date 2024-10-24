package br.unitins.tp1.irondragon.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class Fornecedor extends DefaultEntity {
    private String nome;
    private String email;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_telefone")
    private TelefoneFornecedor telefone;
}
