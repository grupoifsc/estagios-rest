package com.github.projetoifsc.estagios.core.implementation;

import com.github.projetoifsc.estagios.core.models.IJob;
import com.github.projetoifsc.estagios.core.models.IOrg;
import com.github.projetoifsc.estagios.core.IOrganizationDAO;

import java.util.List;

class Org implements IOrg {

    private final IOrg orgData;
    private final IOrganizationDAO orgDB;

    private Org(IOrg orgData, IOrganizationDAO orgDB) {
        this.orgData = orgData;
        this.orgDB = orgDB;
    }

    public static Org createFromId(String id, IOrganizationDAO orgDB) {
        var orgData = orgDB.findById(id);
        return new Org(orgData, orgDB);
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

    public boolean isReceiver( List<IOrg> receiversList) {
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
