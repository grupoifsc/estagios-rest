package com.github.projetoifsc.estagios.core.implementation;

import com.github.projetoifsc.estagios.core.models.IJob;
import com.github.projetoifsc.estagios.core.models.IOrg;
import com.github.projetoifsc.estagios.core.models.projections.ModerationDetailsProjection;

import java.time.LocalDateTime;

class ModerationProjection implements ModerationDetailsProjection {

    private final LocalDateTime modifiedAt;
    private final ModerationStatus status;

    // TODO: ver a questão da vaga!
    public static ModerationProjection resolve(IOrg org, IJob job) {
        if(OrganizationValidation.isOwner(org, job)) {
            return new ModerationProjection("owner", null);
        }
        return new ModerationProjection("pendente", null);
    }

    private ModerationProjection(String status, LocalDateTime modifiedAt) {
        this.modifiedAt = modifiedAt;
        this.status = new ModerationStatus(status);
    }

    @Override
    public LocalDateTime getModifiedAt() {
        return modifiedAt;
    }

    @Override
    public ModerationStatus getStatus() {
        return status;
    }

    public static class ModerationStatus implements ModerationStatusProjection {

        private final String name;

        private ModerationStatus(String name) {
            this.name = name;
        }

        @Override
        public String getName() {
            return name;
        }

    }
}
