package com.github.projetoifsc.estagios.core.dto;


import com.github.projetoifsc.estagios.core.models.IOrg;
import com.github.projetoifsc.estagios.core.models.projections.JobSummaryProjection;
import com.github.projetoifsc.estagios.core.models.projections.OrgSummaryProjection;

import java.util.List;

public class JobImpl implements JobSummaryProjection {

    private String id;
    private OrgSummaryProjection owner;
    private List<OrgSummaryProjection> exclusiveReceivers;

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    @Override
    public OrgSummaryProjection getOwner() {
        return owner;
    }

    @Override
    public void setOwner(IOrg user) {
        this.owner = new OrgImpl(user.getId(), user.getIe());
    }

    public void setOwner(OrgSummaryProjection owner) {
        this.owner = owner;
    }

    @Override
    public List<OrgSummaryProjection> getExclusiveReceivers() {
        return exclusiveReceivers;
    }

    public void setExclusiveReceivers(List<OrgSummaryProjection> exclusiveReceivers) {
        this.exclusiveReceivers = exclusiveReceivers;
    }

}
