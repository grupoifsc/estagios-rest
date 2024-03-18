package com.github.projetoifsc.estagios.core.domain.usecases;

import com.github.projetoifsc.estagios.core.domain.IOrganization;
import com.github.projetoifsc.estagios.core.domain.IOrganizationRepository;
import com.github.projetoifsc.estagios.core.exceptions.UnauthorizedAccessException;
import static com.github.projetoifsc.estagios.core.domain.usecases.OrganizationValidation.canAccessPrivateProfile;

public class CreateOrganizationUseCases {

    IOrganizationRepository organizationRepository;

    public CreateOrganizationUseCases(IOrganizationRepository organizationRepository) {
        this.organizationRepository = organizationRepository;
    }


    public IOrganization createProfile(IOrganization organization) {
        return organizationRepository.save(organization);
    }


    public IOrganization updateProfile(String loggedId, String targetId, IOrganization organization) {
        if(canAccessPrivateProfile(loggedId, targetId)) {
            organization.setId(targetId);
            return organizationRepository.save(organization);
        }
        var exceptionMessage = "Organizations can only update their own profiles";
        throw new UnauthorizedAccessException(exceptionMessage);
    }


    public void deleteProfile(String loggedId, String targetId) {
        if(canAccessPrivateProfile(loggedId, targetId)){
            organizationRepository.delete(targetId);
            return;
        }
        var exceptionMessage = "Organizations can only delete their own profiles";
        throw new UnauthorizedAccessException(exceptionMessage);
    }


    public IOrganization getPrivateProfile(String loggedId, String targetId) {
        if(canAccessPrivateProfile(loggedId, targetId))
            return organizationRepository.getPrivateProfile(targetId);
        var exceptionMessage = "Organizations can only see their own private profiles";
        throw new UnauthorizedAccessException(exceptionMessage);
    }


    public IOrganization getPublicProfile(String loggedId, String targetId) {
        return organizationRepository.getPublicProfile(targetId);
    }


}
