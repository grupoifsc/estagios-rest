package com.github.projetoifsc.estagios.core.domain.usecases;

import com.github.projetoifsc.estagios.core.domain.IOrganization;
import com.github.projetoifsc.estagios.core.domain.IOrganizationRepository;

import java.util.List;

public class GetAllOrganizations {

    IOrganizationRepository organizationRepository;

    public GetAllOrganizations(IOrganizationRepository organizationRepository) {
        this.organizationRepository = organizationRepository;
    }

    public List<IOrganization> getAll() {
        return this.organizationRepository.getAllPublicProfile();
    }

    public List<IOrganization> getSchools() {
        return this.organizationRepository.getSchoolsPublicProfile();
    }

}
