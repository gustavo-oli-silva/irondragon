package br.unitins.tp1.irondragon.model.pedido;

import br.unitins.tp1.irondragon.model.Cidade;
import br.unitins.tp1.irondragon.model.DefaultEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@Entity
public class EnderecoEntrega extends DefaultEntity {
    private String logradouro;
    private String cep;
    private String bairro;
    private String complemento;
    private Integer numero;

    @ManyToOne
    @JoinColumn(name = "id_cidade")
    private Cidade cidade;
}
