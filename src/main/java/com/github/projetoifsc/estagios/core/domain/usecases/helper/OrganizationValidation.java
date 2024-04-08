package com.github.projetoifsc.estagios.core.domain.usecases.helper;

import com.github.projetoifsc.estagios.core.domain.IOrganization;
import com.github.projetoifsc.estagios.core.domain.iJob;

import java.util.List;

public class OrganizationValidation {

    public static boolean isSelf(String loggedId, String targetId) {
        return loggedId
                .equalsIgnoreCase(targetId);
    }

    public static boolean isReceiver(IOrganization organization, List<IOrganization> receiversList) {
        return receiversList.isEmpty() || receiversList.contains(organization);
    }

    public static boolean isOwner(IOrganization organization, iJob traineeship) {
        return isSelf(organization.getId(), traineeship.getOwner().getId());
    }

    public static boolean isValidReceiver(IOrganization organization) {
        return organization.isSchool();
    }
}
