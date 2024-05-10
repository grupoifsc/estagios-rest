package com.github.projetoifsc.estagios.utils;

import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class Mapper {

    ModelMapper modelMapper = new ModelMapper();

    public Mapper() {
        modelMapper.getConfiguration()
                .setPropertyCondition(Conditions.isNotNull());
    }

    public <T> T map(Object source, Class<T> destinationType) {
        return modelMapper.map(source, destinationType);
    }

    public void map(Object source, Object destination) {
        modelMapper.map(source, destination);
    }

}
