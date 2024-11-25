package br.unitins.tp1.irondragon.model.converterjpa;

import br.unitins.tp1.irondragon.model.usuario.Perfil;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class PerfilConverter implements AttributeConverter<Perfil, Integer> {

    @Override
    public Integer convertToDatabaseColumn(Perfil attribute) {
        return attribute.getId();
    }

    @Override
    public Perfil convertToEntityAttribute(Integer dbData) {
        return Perfil.valueOf(dbData);
    }
}
