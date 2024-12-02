package br.unitins.tp1.irondragon.model.usuario;

import br.unitins.tp1.irondragon.model.Cartao;
import br.unitins.tp1.irondragon.model.DefaultEntity;
import br.unitins.tp1.irondragon.model.processador.Processador;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@Entity
public class Cliente extends DefaultEntity {
    @OneToOne
    @JoinColumn(name = "id_usuario", unique = true, nullable = false)
    private Usuario usuario;

    @OneToMany
    @JoinColumn(name = "id_processador")
    private List<Processador> listaDeDesejos;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_cartao")
    List<Cartao> listaDeCartoes;
}
