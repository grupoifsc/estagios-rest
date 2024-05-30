package com.github.projetoifsc.estagios.app.configs;

import com.github.projetoifsc.estagios.core.*;
import com.github.projetoifsc.estagios.core.implementation.AreaUseCases;
import com.github.projetoifsc.estagios.core.implementation.JobUseCases;
import com.github.projetoifsc.estagios.core.implementation.OrganizationUseCases;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProviderConfig {


    @Bean
    public IOrganizationUseCases defaultOrganizationUseCases(IOrganizationDB iOrganizationDB) {
        return new OrganizationUseCases(iOrganizationDB);
    }


    @Bean
    public IAreaUseCases defaultAreaUseCases(IAreaDB iAreaDB) {
        return new AreaUseCases(iAreaDB);
    }


    @Bean
    public IJobUseCases defaultJobUseCases(IJobDB iJobDB, IOrganizationDB iOrganizationDB) {
        return new JobUseCases(iJobDB, iOrganizationDB);
    }


}
