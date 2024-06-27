package com.github.projetoifsc.estagios.infra.db.jpa;

import com.github.projetoifsc.estagios.core.models.projections.JobBasicProjection;
import com.github.projetoifsc.estagios.core.models.projections.OrgSummaryProjection;

import java.util.ArrayList;
import java.util.List;

class JobBasicProjectionDTO implements JobBasicProjection {

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
    public List<OrgSummaryProjection> getExclusiveReceivers() {
        return exclusiveReceivers;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setOwner(OrgSummaryProjectionDTO owner) {
        this.owner = owner;
    }

    public void setExclusiveReceivers(List<OrgSummaryProjection> exclusiveReceivers) {
        this.exclusiveReceivers = exclusiveReceivers;
    }
}
