package com.github.projetoifsc.estagios.core.implementation;

import com.github.projetoifsc.estagios.app.model.interfaces.INewUser;
import com.github.projetoifsc.estagios.core.IOrganization;
import com.github.projetoifsc.estagios.core.IOrganizationDAO;

class OrganizationWriteOperations {

    IOrganizationDAO organizationRepository;

    public OrganizationWriteOperations(IOrganizationDAO organizationRepository) {
        this.organizationRepository = organizationRepository;
    }


    public IOrganization createProfile(INewUser organization) {
        return organizationRepository.save(organization);
    }


    public IOrganization updateProfile(String loggedId, String targetId, INewUser organization) {
        if(OrganizationValidation.isSelf(loggedId, targetId)) {
            organization.setId(targetId);
            return organizationRepository.save(organization);
        }
        var exceptionMessage = "Organizations can only update their own profiles";
        throw new UnauthorizedAccessException(exceptionMessage);
    }


    public void deleteProfile(String loggedId, String targetId) {
        if(OrganizationValidation.isSelf(loggedId, targetId)){
            organizationRepository.delete(targetId);
            return;
        }
        var exceptionMessage = "Organizations can only delete their own profiles";
        throw new UnauthorizedAccessException(exceptionMessage);
    }


}
