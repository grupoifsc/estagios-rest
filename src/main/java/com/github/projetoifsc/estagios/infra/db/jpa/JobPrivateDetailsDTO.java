package com.github.projetoifsc.estagios.infra.db.jpa;

import com.github.projetoifsc.estagios.core.models.IOrg;
import com.github.projetoifsc.estagios.core.models.projections.JobPrivateDetailsProjection;
import com.github.projetoifsc.estagios.core.models.projections.OrgSummaryProjection;

import java.util.ArrayList;
import java.util.List;

public class JobPrivateDetailsDTO extends JobPublicDetailsDTO implements JobPrivateDetailsProjection {

    private List<OrgSummaryProjection> exclusiveReceivers = new ArrayList<>();

    @Override
    public List<OrgSummaryProjection> getExclusiveReceivers() {
        return exclusiveReceivers;
    }

    public void setExclusiveReceivers(List<OrgSummaryProjectionDTO> exclusiveReceivers) {
        this.exclusiveReceivers.addAll(exclusiveReceivers);
    }

}
