package com.github.projetoifsc.estagios.core.domain.usecases;

public class OrganizationValidation {

    public static boolean canGetAllTraineeships(String loggedId, String targetId) {
        return loggedId.equalsIgnoreCase(targetId);
    }

    public static boolean canAccessPrivateProfile(String organizationId, String targetId) {
        return organizationId
                .equalsIgnoreCase(targetId);
    }


}
