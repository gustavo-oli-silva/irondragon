package br.unitins.tp1.irondragon.model.converterjpa;

import br.unitins.tp1.irondragon.model.Sexo;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class SexoConverter implements AttributeConverter<Sexo, Integer> {
    @Override
    public Integer convertToDatabaseColumn(Sexo attribute) {
        return attribute == null ? null : attribute.getId();
    }

    @Override
    public Sexo convertToEntityAttribute(Integer dbData) {
        return Sexo.valueOf(dbData);
    }
}
