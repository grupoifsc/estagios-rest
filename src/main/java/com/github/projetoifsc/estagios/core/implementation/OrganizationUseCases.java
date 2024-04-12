package com.github.projetoifsc.estagios.core.implementation;

import com.github.projetoifsc.estagios.core.IOrganization;
import com.github.projetoifsc.estagios.core.IOrganizationRepository;

import java.util.List;

class OrganizationUseCases {

    IOrganizationRepository organizationRepository;

    // TODO: Checar se é assim! Criando novos objetos a cada requisição? Impressão de que não era pra ser assim
    OrganizationWriteOperations writeUC = new OrganizationWriteOperations(organizationRepository);
    OrganizationReadOperations readUC = new OrganizationReadOperations(organizationRepository);

    public OrganizationUseCases(IOrganizationRepository organizationRepository) {
        this.organizationRepository = organizationRepository;
    }

    public IOrganization createProfile(IOrganization organization) {
        return writeUC.createProfile(organization);
    }

    public IOrganization updateProfile(String loggedId, String targetId, IOrganization organization) {
        return writeUC.updateProfile(loggedId, targetId, organization);
    }

    public void deleteProfile(String loggedId, String targetId) {
        writeUC.deleteProfile(loggedId, targetId);
    }

    public List<IOrganization> getAll() {
        return readUC.getAll();
    }

    public List<IOrganization> getSchools() {
        return readUC.getSchools();
    }

    public IOrganization getPrivateProfile(String loggedId, String targetId) {
        return readUC.getPrivateProfile(loggedId, targetId);
    }

    public IOrganization getPublicProfile(String loggedId, String targetId) {
        return readUC.getPublicProfile(loggedId, targetId);
    }

}
