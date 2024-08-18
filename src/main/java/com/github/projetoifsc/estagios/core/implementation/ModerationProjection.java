package com.github.projetoifsc.estagios.core.implementation;

import com.github.projetoifsc.estagios.core.models.IJob;
import com.github.projetoifsc.estagios.core.models.IOrg;
import com.github.projetoifsc.estagios.core.models.projections.ModerationDetailsProjection;

import java.time.LocalDateTime;

class ModerationResolver {

    /**
     * Modifica o status da Moderação
     * @param org
     * @param job
     * @param moderation
     * @return
     */
    public static ModerationDetailsProjection resolve(IOrg org, IJob job, ModerationDetailsProjection moderation) {
        if(OrganizationValidation.isOwner(org, job)) {
            moderation.setStatus(EModerationStatus.OWNER.getNamePtBR());
        } else if (moderation.getStatus() != null) {
            moderation.setStatus(
                    moderation.getStatus().equalsIgnoreCase("aprovado") ?
                            EModerationStatus.ACCEPTED.getNamePtBR() :
                            EModerationStatus.REJECTED.getNamePtBR()
            );
        } else {
            moderation.setStatus(EModerationStatus.PENDING.getNamePtBR());
        }
        return moderation;
    }

}
