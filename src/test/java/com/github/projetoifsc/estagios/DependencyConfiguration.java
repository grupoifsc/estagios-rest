package com.github.projetoifsc.estagios;

import com.github.projetoifsc.estagios.core.IAreaRepository;
import com.github.projetoifsc.estagios.core.IAreaUseCases;
import com.github.projetoifsc.estagios.core.implementation.AreaUseCases;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class DependencyConfiguration {

    @Autowired
    IAreaRepository areaRepositoryImpl;

    @Bean
    public IAreaUseCases setAreaUseCase() {
        return new AreaUseCases(areaRepositoryImpl, null);
    }

}
