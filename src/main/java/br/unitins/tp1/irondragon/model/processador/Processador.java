package br.unitins.tp1.irondragon.model.processador;

import br.unitins.tp1.irondragon.model.Fabricante;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Processador extends PanacheEntityBase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String socket;
    private Integer threads;
    private Integer nucleos;
    private Boolean desbloqueado;
    private Double preco;

    @ManyToOne
    @JoinColumn(name = "id_fabricante")
    private Fabricante fabricante;

    @ManyToOne
    @JoinColumn(name = "id_placaintegrada")
    private PlacaIntegrada placaIntegrada;
}
