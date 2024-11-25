package br.unitins.tp1.irondragon.model.pagamento;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class Pix extends Pagamento {
    private String chave;
    private String destinatario;
    private LocalDateTime dataValidade;
}
