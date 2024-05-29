package com.github.projetoifsc.estagios.core.implementation;

import com.github.projetoifsc.estagios.core.IOrganization;
import com.github.projetoifsc.estagios.core.IOrganizationDB;
import org.springframework.data.domain.Page;

class OrganizationReadOperations {

    IOrganizationDB organizationRepository;

    public OrganizationReadOperations(IOrganizationDB organizationRepository) {
        this.organizationRepository = organizationRepository;
    }

    public Page<IOrganization> getSchools() {
        return this.organizationRepository.getAllSchoolsPublicProfile();
    }

    public IOrganization getPrivateProfile(String loggedId, String targetId) {
        if(OrganizationValidation.isSelf(loggedId, targetId))
            return organizationRepository.getOnePrivateProfile(targetId);
        var exceptionMessage = "Organizations can only see their own private profiles";
        throw new UnauthorizedAccessException(exceptionMessage);
    }


    public IOrganization getPublicProfile(String loggedId, String targetId) {
        return organizationRepository.getOnePublicProfile(targetId);
    }


}
