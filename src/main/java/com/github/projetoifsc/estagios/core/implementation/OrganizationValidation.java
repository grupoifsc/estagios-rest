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
        var orgId = organization.getId();
        var jobOwnerId = traineeship.getOwner().getId();
        return isSelf(orgId, jobOwnerId);
    }

    public static boolean isValidReceiver(IOrganization organization) {
        return organization.getIe();
    }

    public static boolean isIE(IOrganization organization) {
        return organization.getIe();
    }

}
