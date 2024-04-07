package com.github.projetoifsc.estagios.core.domain.usecases;

import com.github.projetoifsc.estagios.core.domain.IOrganizationRepository;

public class GetAllOrganizations {

    IOrganizationRepository organizationRepository;

    public GetAllOrganizations(IOrganizationRepository organizationRepository) {
        this.organizationRepository = organizationRepository;
    }

    // TODO Teste Educacionais
    // TODO Teste Todas em Geral

}
