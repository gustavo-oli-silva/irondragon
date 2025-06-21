package br.unitins.tp1.irondragon.model.processador;

import br.unitins.tp1.irondragon.listener.ProcessadorListener;
import br.unitins.tp1.irondragon.model.DefaultEntity;
import br.unitins.tp1.irondragon.model.Fabricante;
import br.unitins.tp1.irondragon.model.ImagemProcessador;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@Entity
@EntityListeners(ProcessadorListener.class)
public class Processador extends DefaultEntity {
    private String nome;
    private String socket;
    private Integer threads;
    private Integer nucleos;
    private Boolean desbloqueado;
    private Double preco;

    @Transient
    private Integer quantidade;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_processador")
    private List<ImagemProcessador> imagens;

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
