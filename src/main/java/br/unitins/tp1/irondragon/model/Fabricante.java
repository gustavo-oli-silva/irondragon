package br.unitins.tp1.irondragon.model;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;

@Entity
public class Fabricante extends DefaultEntity {
    private String nome;
    private String email;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_telefone")
    private TelefoneFabricante telefone;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public TelefoneFabricante getTelefone() {
        return telefone;
    }

    public void setTelefone(TelefoneFabricante telefone) {
        this.telefone = telefone;
    }
}
