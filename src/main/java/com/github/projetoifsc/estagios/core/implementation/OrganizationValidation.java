package com.github.projetoifsc.estagios.core.implementation;

import com.github.projetoifsc.estagios.core.IOrganization;
import com.github.projetoifsc.estagios.core.IJob;

import java.util.List;

class OrganizationValidation {

    public static boolean isSelf(String loggedId, String targetId) {
        return loggedId
                .equalsIgnoreCase(targetId);
    }

    public static boolean isReceiver(IOrganization organization, List<IOrganization> receiversList) {
        return receiversList.isEmpty() || receiversList.contains(organization);
    }

    public static boolean isOwner(IOrganization organization, IJob traineeship) {
        return isSelf(organization.getId(), traineeship.getOwner().getId());
    }

    public static boolean isValidReceiver(IOrganization organization) {
        return organization.isSchool();
    }

}
