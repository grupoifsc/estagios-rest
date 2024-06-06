package com.github.projetoifsc.estagios.app.configs;

import com.github.projetoifsc.estagios.core.*;
import com.github.projetoifsc.estagios.core.implementation.AreaUseCases;
import com.github.projetoifsc.estagios.core.implementation.JobUseCases;
import com.github.projetoifsc.estagios.core.implementation.OrganizationUseCases;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCasesResolverConfig {

    @Bean
    public IOrganizationUseCases defaultOrganizationUseCases(IOrganizationDAO iOrganizationDAO) {
        return new OrganizationUseCases(iOrganizationDAO);
    }

    @Bean
    public IAreaUseCases defaultAreaUseCases(IAreaDAO iAreaDAO) {
        return new AreaUseCases(iAreaDAO);
    }

    @Bean
    public IJobUseCases defaultJobUseCases(IJobDAO iJobDAO, IOrganizationDAO iOrganizationDAO) {
        return new JobUseCases(iJobDAO, iOrganizationDAO);
    }


}
