package com.github.projetoifsc.estagios.core.domain.usecases;

import com.github.projetoifsc.estagios.core.domain.IOrganization;
import com.github.projetoifsc.estagios.core.domain.IOrganizationRepository;
import com.github.projetoifsc.estagios.core.exceptions.UnauthorizedAccessException;
import static com.github.projetoifsc.estagios.core.domain.usecases.helper.OrganizationValidation.isSelf;

public class CreateOrganization {

    IOrganizationRepository organizationRepository;

    public CreateOrganization(IOrganizationRepository organizationRepository) {
        this.organizationRepository = organizationRepository;
    }


    public IOrganization createProfile(IOrganization organization) {
        return organizationRepository.save(organization);
    }


    public IOrganization updateProfile(String loggedId, String targetId, IOrganization organization) {
        if(isSelf(loggedId, targetId)) {
            organization.setId(targetId);
            return organizationRepository.save(organization);
        }
        var exceptionMessage = "Organizations can only update their own profiles";
        throw new UnauthorizedAccessException(exceptionMessage);
    }


    public void deleteProfile(String loggedId, String targetId) {
        if(isSelf(loggedId, targetId)){
            organizationRepository.delete(targetId);
            return;
        }
        var exceptionMessage = "Organizations can only delete their own profiles";
        throw new UnauthorizedAccessException(exceptionMessage);
    }


}
