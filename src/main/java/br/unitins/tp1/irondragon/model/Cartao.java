package br.unitins.tp1.irondragon.model;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.time.LocalDate;
import java.util.Objects;

@Entity
public class Cartao extends DefaultEntity {
    private String nomeTitular;
    private String numero;
    private String cpf;
    private LocalDate validade;
    private Integer cvc;
    private TipoCartao tipo;

    public String getNomeTitular() {
        return nomeTitular;
    }

    public void setNomeTitular(String nomeTitular) {
        this.nomeTitular = nomeTitular;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public LocalDate getValidade() {
        return validade;
    }

    public void setValidade(LocalDate validade) {
        this.validade = validade;
    }

    public Integer getCvc() {
        return cvc;
    }

    public void setCvc(Integer cvc) {
        this.cvc = cvc;
    }

    public TipoCartao getTipo() {
        return tipo;
    }

    public void setTipo(TipoCartao tipo) {
        this.tipo = tipo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cartao cartao)) return false;
        return Objects.equals(nomeTitular, cartao.nomeTitular) && Objects.equals(numero, cartao.numero) && Objects.equals(cpf, cartao.cpf) && Objects.equals(validade, cartao.validade) && Objects.equals(cvc, cartao.cvc) && tipo == cartao.tipo;
    }

    @Override
    public int hashCode() {
        return Objects.hash(nomeTitular, numero, cpf, validade, cvc, tipo);
    }
}
