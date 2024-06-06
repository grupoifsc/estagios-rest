package com.github.projetoifsc.estagios.core.implementation;

import com.github.projetoifsc.estagios.core.IAddress;
import com.github.projetoifsc.estagios.core.IContact;
import com.github.projetoifsc.estagios.core.IOrganization;
import com.github.projetoifsc.estagios.core.IOrganizationDAO;
import org.springframework.data.domain.Page;

import java.util.List;

class OrganizationReadOperations {

    IOrganizationDAO organizationRepository;

    public OrganizationReadOperations(IOrganizationDAO organizationRepository) {
        this.organizationRepository = organizationRepository;
    }

    public IOrganization getPublicProfile(String loggedId, String targetId) {
        return organizationRepository.getOnePublicProfile(targetId);
    }


    public IOrganization getPrivateProfile(String loggedId, String targetId) {
        if(OrganizationValidation.isSelf(loggedId, targetId))
            return organizationRepository.getOnePrivateProfile(targetId);
        var exceptionMessage = "Organizations can only see their own private profiles";
        throw new UnauthorizedAccessException(exceptionMessage);
    }


    public Page<IOrganization> getAllSchools() {
        return this.organizationRepository.getAllSchoolsPublicProfile();
    }


    public List<IAddress> getAddresses(String loggedId, String targetId) {
        if(OrganizationValidation.isSelf(loggedId, targetId))
            return organizationRepository.getAllAddresses(targetId);
        var exceptionMessage = "Organizations can only see their own private profiles";
        throw new UnauthorizedAccessException(exceptionMessage);
    }

    public List<IContact> getContacts(String loggedId, String targetId) {
        if(OrganizationValidation.isSelf(loggedId, targetId))
            return organizationRepository.getAllContacts(targetId);
        var exceptionMessage = "Organizations can only see their own private profiles";
        throw new UnauthorizedAccessException(exceptionMessage);
    }

}
