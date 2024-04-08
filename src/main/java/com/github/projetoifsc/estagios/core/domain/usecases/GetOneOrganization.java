package com.github.projetoifsc.estagios.core.domain.usecases;

import com.github.projetoifsc.estagios.core.domain.IOrganization;
import com.github.projetoifsc.estagios.core.domain.IOrganizationRepository;
import com.github.projetoifsc.estagios.core.exceptions.UnauthorizedAccessException;

import static com.github.projetoifsc.estagios.core.domain.usecases.helper.OrganizationValidation.isSelf;

public class GetOneOrganization {

    IOrganizationRepository organizationRepository;

    public GetOneOrganization(IOrganizationRepository organizationRepository) {
        this.organizationRepository = organizationRepository;
    }


    public IOrganization getPrivateProfile(String loggedId, String targetId) {
        if(isSelf(loggedId, targetId))
            return organizationRepository.getPrivateProfile(targetId);
        var exceptionMessage = "Organizations can only see their own private profiles";
        throw new UnauthorizedAccessException(exceptionMessage);
    }


    public IOrganization getPublicProfile(String loggedId, String targetId) {
        return organizationRepository.getPublicProfile(targetId);
    }



}
