package br.unitins.tp1.irondragon.model.usuario;

import br.unitins.tp1.irondragon.model.DefaultEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
public class Funcionario extends DefaultEntity {
    @OneToOne
    @JoinColumn(name = "id_usuario", unique = true)
    private Usuario usuario;
    private LocalDate dataContratacao;
    private String cargo;
    private Double salario;
}
