package com.github.projetoifsc.estagios.infra.db.jpa;

import com.github.projetoifsc.estagios.core.models.IOrg;
import com.github.projetoifsc.estagios.core.models.projections.JobSummaryProjection;
import com.github.projetoifsc.estagios.core.models.projections.OrgSummaryProjection;

import java.util.ArrayList;
import java.util.List;

class JobSummaryProjectionDTO implements JobSummaryProjection {

    private String id;
    private OrgSummaryProjectionDTO owner;
    private List<OrgSummaryProjection> exclusiveReceivers = new ArrayList<>();

    @Override
    public String getId() {
        return id;
    }

    @Override
    public OrgSummaryProjectionDTO getOwner() {
        return owner;
    }

    @Override
    public void setOwner(IOrg owner) {
        this.owner = new OrgSummaryProjectionDTO();
        this.owner.setId(owner.getId());
        this.owner.setIe(owner.getIe());
    }

    @Override
    public List<OrgSummaryProjection> getExclusiveReceivers() {
        return exclusiveReceivers;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setOwner(OrgSummaryProjectionDTO owner) {
        this.owner = owner;
    }

    public void setExclusiveReceivers(List<OrgSummaryProjectionDTO> exclusiveReceivers) {
        this.exclusiveReceivers.addAll(exclusiveReceivers);
    }

}
