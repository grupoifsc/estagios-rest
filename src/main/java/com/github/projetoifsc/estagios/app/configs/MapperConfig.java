package com.github.projetoifsc.estagios.app.configs;

import com.github.projetoifsc.estagios.app.utils.Mapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfig {

    @Bean
    public Mapper defaultMapper() {
        return new Mapper();
    }

}
