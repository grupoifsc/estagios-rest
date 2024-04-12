package com.github.projetoifsc.estagios;

import com.github.projetoifsc.estagios.core.IAreaRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class DatabaseConfig {

    @Bean
    public IAreaRepository areaRepositoryImpl() {
        return new AreaRepositoryImple();
    }

}
