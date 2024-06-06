package com.github.projetoifsc.estagios.core.implementation;

import com.github.projetoifsc.estagios.core.IJob;
import com.github.projetoifsc.estagios.core.IOrganization;
import com.github.projetoifsc.estagios.core.IOrganizationDAO;

import java.util.List;

class Organization implements IOrganization {

    private final IOrganization orgData;
    private final IOrganizationDAO orgDB;

    private Organization(IOrganization orgData, IOrganizationDAO orgDB) {
        this.orgData = orgData;
        this.orgDB = orgDB;
    }

    public static Organization createFromId(String id, IOrganizationDAO orgDB) {
        var orgData = orgDB.findById(id);
        return new Organization(orgData, orgDB);
    }

    @Override
    public String getId() {
        return orgData.getId();
    }

    @Override
    public void setId(String id) {
        orgData.setId(id);
    }

    @Override
    public Boolean getIe() {
        return orgData.getIe();
    }

    public boolean isSelf(String targetId) {
        return this.getId().equalsIgnoreCase(targetId);
    }

    public boolean isReceiver( List<IOrganization> receiversList) {
        return receiversList.isEmpty() || receiversList.contains(this);
    }

    public boolean isOwner(IJob traineeship) {
        return this.getId().equalsIgnoreCase(traineeship.getOwner().getId());
    }

    public boolean isValidReceiver() {
        return this.getIe();
    }

    public boolean isIe() {
        return this.getIe();
    }

}
