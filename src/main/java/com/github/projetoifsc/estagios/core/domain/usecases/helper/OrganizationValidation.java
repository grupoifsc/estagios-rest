package com.github.projetoifsc.estagios.core.domain.usecases.helper;

import com.github.projetoifsc.estagios.core.domain.iOrganization;
import com.github.projetoifsc.estagios.core.domain.iJob;

import java.util.List;

public class OrganizationValidation {

    public static boolean isSelf(String loggedId, String targetId) {
        return loggedId
                .equalsIgnoreCase(targetId);
    }

    public static boolean isReceiver(iOrganization organization, List<iOrganization> receiversList) {
        return receiversList.isEmpty() || receiversList.contains(organization);
    }

    public static boolean isOwner(iOrganization organization, iJob traineeship) {
        return isSelf(organization.getId(), traineeship.getOwner().getId());
    }

    public static boolean isValidReceiver(iOrganization organization) {
        return organization.isSchool();
    }
}
