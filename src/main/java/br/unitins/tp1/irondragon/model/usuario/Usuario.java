package br.unitins.tp1.irondragon.model.usuario;

import br.unitins.tp1.irondragon.model.DefaultEntity;
import br.unitins.tp1.irondragon.model.Endereco;
import br.unitins.tp1.irondragon.model.Perfil;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
public class Usuario extends DefaultEntity {
    @Column(unique = true)
    private String username;
    @Column(unique = true)
    private String email;
    private String senha;
    private Perfil perfil;

    private LocalDateTime dataCriacao;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "id_endereco")
    List<Endereco> enderecos;
}
