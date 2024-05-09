package com.github.projetoifsc.estagios.app.configs;

import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    ModelMapper mapper = new ModelMapper();

    @Bean
    public ModelMapper getModelMapper() {
        configModelMapper(mapper);
        return mapper;
    }

    private void configModelMapper(ModelMapper mapper) {
        mapper.getConfiguration()
                .setPropertyCondition(Conditions.isNotNull());
    }

}
