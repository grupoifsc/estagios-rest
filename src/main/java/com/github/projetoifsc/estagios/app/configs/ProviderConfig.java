package com.github.projetoifsc.estagios.app.configs;

import com.github.projetoifsc.estagios.core.IAreaDB;
import com.github.projetoifsc.estagios.core.IAreaUseCases;
import com.github.projetoifsc.estagios.core.IOrganizationDB;
import com.github.projetoifsc.estagios.core.IOrganizationUseCases;
import com.github.projetoifsc.estagios.core.implementation.AreaUseCases;
import com.github.projetoifsc.estagios.core.implementation.OrganizationUseCases;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProviderConfig {

//    IOrganizationDB iOrganizationDB;
//
//    @Autowired
//    ProviderConfig(IOrganizationDB iOrganizationDB) {
//        this.iOrganizationDB = iOrganizationDB;
//    }

    @Autowired
    @Bean
    public IOrganizationUseCases defaultOrganizationUseCases(IOrganizationDB iOrganizationDB) {
        return new OrganizationUseCases(iOrganizationDB);
    }


    @Autowired
    @Bean
    public IAreaUseCases defaultAreaUseCases(IAreaDB iAreaDB) {
        return new AreaUseCases(iAreaDB);
    }

}
