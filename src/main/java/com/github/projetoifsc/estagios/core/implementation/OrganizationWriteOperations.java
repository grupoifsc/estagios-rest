package com.github.projetoifsc.estagios.core.implementation;

import com.github.projetoifsc.estagios.core.IOrganizationDAO;
import com.github.projetoifsc.estagios.core.models.IOrgEntryData;
import com.github.projetoifsc.estagios.core.models.projections.OrgPrivateProfileProjection;

class OrganizationWriteOperations {

    IOrganizationDAO organizationRepository;

    public OrganizationWriteOperations(IOrganizationDAO organizationRepository) {
        this.organizationRepository = organizationRepository;
    }


    public OrgPrivateProfileProjection createProfile(IOrgEntryData organization) {
        return organizationRepository.save(organization);
    }


    public OrgPrivateProfileProjection updateProfile(String loggedId, String targetId, IOrgEntryData organization) {
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
