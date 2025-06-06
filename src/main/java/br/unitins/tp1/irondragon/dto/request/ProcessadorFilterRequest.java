package br.unitins.tp1.irondragon.dto.request;

import java.math.BigDecimal;
import java.util.List;

public record ProcessadorFilterRequest(
                String nome,
                String fabricante,
                Double precoMin,
                Double precoMax,
                List<String> sockets,
                String graficosIntegrados,
                String sortBy
                ) {

}
