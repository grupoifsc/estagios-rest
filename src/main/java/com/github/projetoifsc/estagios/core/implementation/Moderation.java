package com.github.projetoifsc.estagios.core.implementation;

import com.github.projetoifsc.estagios.core.models.IJob;
import com.github.projetoifsc.estagios.core.models.IOrganization;
import com.github.projetoifsc.estagios.core.models.JobPublicSummaryProjection;
import com.github.projetoifsc.estagios.core.models.ModerationProjection;

import java.time.LocalDateTime;

class Moderation implements ModerationProjection{

    private final LocalDateTime modifiedAt;
    private final ModerationStatus status;

    public static Moderation resolve(IOrganization org, IJob job) {
        if(OrganizationValidation.isOwner(org, job)) {
            return new Moderation("owner", job.getCreatedAt());
        }
        return new Moderation("pendente", job.getCreatedAt());
    }

    private Moderation(String status, LocalDateTime modifiedAt) {
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
