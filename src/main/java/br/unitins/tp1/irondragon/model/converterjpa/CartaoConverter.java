package br.unitins.tp1.irondragon.model.converterjpa;

import br.unitins.tp1.irondragon.model.TipoCartao;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class CartaoConverter implements AttributeConverter<TipoCartao, Integer> {

    @Override
    public Integer convertToDatabaseColumn(TipoCartao tipoCartao) {
        return tipoCartao.getId();
    }

    @Override
    public TipoCartao convertToEntityAttribute(Integer integer) {
        return TipoCartao.valueOf(integer);
    }

}
