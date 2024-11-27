package br.unitins.tp1.irondragon.model.converterjpa;

import br.unitins.tp1.irondragon.model.pedido.StatusPedido;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class StatusPedidoConverter implements AttributeConverter<StatusPedido, Integer> {
    @Override
    public Integer convertToDatabaseColumn(StatusPedido statusPedido) {
        return statusPedido.getId();
    }

    @Override
    public StatusPedido convertToEntityAttribute(Integer integer) {
        return StatusPedido.valueOf(integer);
    }
}
