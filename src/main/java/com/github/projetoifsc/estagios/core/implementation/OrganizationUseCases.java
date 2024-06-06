package com.github.projetoifsc.estagios.core.implementation;

import com.github.projetoifsc.estagios.app.model.interfaces.INewUser;
import com.github.projetoifsc.estagios.core.*;
import org.springframework.data.domain.Page;

import java.util.List;


public class OrganizationUseCases implements IOrganizationUseCases {

    OrganizationWriteOperations writeUC;
    OrganizationReadOperations readUC;

    public OrganizationUseCases(IOrganizationDAO organizationDB) {
        this.readUC = new OrganizationReadOperations(organizationDB);
        this.writeUC = new OrganizationWriteOperations(organizationDB);
    }

    @Override
    public IOrganization createProfile(INewUser organization) {
        return writeUC.createProfile(organization);
    }

    @Override
    public IOrganization updateProfile(String loggedId, String targetId, INewUser organization) {
        return writeUC.updateProfile(loggedId, targetId, organization);
    }

    @Override
    public void deleteProfile(String loggedId, String targetId) {
        writeUC.deleteProfile(loggedId, targetId);
    }

    @Override
    public IOrganization getPublicProfile(String loggedId, String targetId) {
        return readUC.getPublicProfile(loggedId, targetId);
    }

    @Override
    public IOrganization getPrivateProfile(String loggedId, String targetId) {
        return readUC.getPrivateProfile(loggedId, targetId);
    }

    @Override
    public Page<IOrganization> getAllSchools() {
        return readUC.getAllSchools();
    }

    @Override
    public List<IAddress> getAddresses(String loggedId, String targetId) {
        return readUC.getAddresses(loggedId, targetId);
    }

    @Override
    public List<IContact> getContacts(String loggedId, String targetId) {
        return readUC.getContacts(loggedId, targetId);
    }

}
