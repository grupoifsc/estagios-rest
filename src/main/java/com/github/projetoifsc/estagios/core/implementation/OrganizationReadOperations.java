package com.github.projetoifsc.estagios.core.implementation;

import com.github.projetoifsc.estagios.core.IOrganization;
import com.github.projetoifsc.estagios.core.IOrganizationDB;
import org.springframework.data.domain.Page;

import java.util.List;

class OrganizationReadOperations {

    IOrganizationDB organizationRepository;

    public OrganizationReadOperations(IOrganizationDB organizationRepository) {
        this.organizationRepository = organizationRepository;
    }

    public Page<IOrganization> getAll() {
        return this.organizationRepository.getAllPublicProfile();
    }

    public Page<IOrganization> getSchools() {
        return this.organizationRepository.getSchoolsPublicProfile();
    }

    public IOrganization getPrivateProfile(String loggedId, String targetId) {
        if(OrganizationValidation.isSelf(loggedId, targetId))
            return organizationRepository.getPrivateProfile(targetId);
        var exceptionMessage = "Organizations can only see their own private profiles";
        throw new UnauthorizedAccessException(exceptionMessage);
    }


    public IOrganization getPublicProfile(String loggedId, String targetId) {
        return organizationRepository.getPublicProfile(targetId);
    }


}
