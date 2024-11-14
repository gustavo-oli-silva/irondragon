package br.unitins.tp1.irondragon.model.processador;

import br.unitins.tp1.irondragon.model.DefaultEntity;
import br.unitins.tp1.irondragon.model.Fabricante;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Processador extends DefaultEntity {
    private String nome;
    private String socket;
    private Integer threads;
    private Integer nucleos;
    private Boolean desbloqueado;
    private Double preco;

    private String nomeImagem;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "id_memoriacache")
    private MemoriaCache memoriaCache;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "id_frequencia")
    private Frequencia frequencia;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "id_consumoenergetico")
    private ConsumoEnergetico consumoEnergetico;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "id_conectividade")
    private Conectividade conectividade;

    @ManyToOne
    @JoinColumn(name = "id_fabricante")
    private Fabricante fabricante;

    @ManyToOne
    @JoinColumn(name = "id_placaintegrada")
    private PlacaIntegrada placaIntegrada;
}
