package com.github.projetoifsc.estagios.core.implementation;

import com.github.projetoifsc.estagios.core.IOrganization;
import com.github.projetoifsc.estagios.core.IOrganizationDB;
import com.github.projetoifsc.estagios.core.IOrganizationUseCases;
import org.springframework.data.domain.Page;

import java.util.List;

public class OrganizationUseCases implements IOrganizationUseCases {

//    IOrganizationDB organizationDB;

    // TODO: Checar se é assim! Criando novos objetos a cada requisição? Impressão de que não era pra ser assim
    OrganizationWriteOperations writeUC;
    OrganizationReadOperations readUC;

    public OrganizationUseCases(IOrganizationDB organizationDB) {
  //      this.organizationDB = organizationDB;
        this.readUC = new OrganizationReadOperations(organizationDB);
        this.writeUC = new OrganizationWriteOperations(organizationDB);
    }

    @Override
    public IOrganization createProfile(IOrganization organization) {
        return writeUC.createProfile(organization);
    }

    @Override
    public IOrganization updateProfile(String loggedId, String targetId, IOrganization organization) {
        return writeUC.updateProfile(loggedId, targetId, organization);
    }

    @Override
    public void deleteProfile(String loggedId, String targetId) {
        writeUC.deleteProfile(loggedId, targetId);
    }

    @Override
    public Page<IOrganization> getAll() {
        return readUC.getAll();
    }

    @Override
    public Page<IOrganization> getSchools() {
        return readUC.getSchools();
    }

    @Override
    public IOrganization getPrivateProfile(String loggedId, String targetId) {
        return readUC.getPrivateProfile(loggedId, targetId);
    }

    @Override
    public IOrganization getPublicProfile(String loggedId, String targetId) {
        return readUC.getPublicProfile(loggedId, targetId);
    }

}
