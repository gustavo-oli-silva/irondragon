package br.unitins.tp1.irondragon.model.converterjpa;

import br.unitins.tp1.irondragon.model.BandeiraCartao;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class BandeiraCartaoConverter implements AttributeConverter<BandeiraCartao, Integer> {

    @Override
    public Integer convertToDatabaseColumn(BandeiraCartao bandeiraCartao) {
        return bandeiraCartao.getId();
    }

    @Override
    public BandeiraCartao convertToEntityAttribute(Integer integer) {
        return BandeiraCartao.valueOf(integer);
    }

}
