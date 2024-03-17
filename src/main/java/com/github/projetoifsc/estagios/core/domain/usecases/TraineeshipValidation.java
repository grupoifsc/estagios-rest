package com.github.projetoifsc.estagios.core.domain.usecases;

import com.github.projetoifsc.estagios.core.domain.IOrganization;
import com.github.projetoifsc.estagios.core.domain.ITraineeship;
import com.github.projetoifsc.estagios.core.exceptions.InvalidReceiverException;

import java.util.List;

class TraineeshipValidation {

    public static void validateReceivers(List<IOrganization> receivers) {
        var invalidReceiversIds = listInvalidReceiversIds(receivers);
        if(invalidReceiversIds.isEmpty()) {
            return;
        }
        var exceptionMessage = generateInvalidReceiversMessage(invalidReceiversIds);
        throw new InvalidReceiverException(exceptionMessage);
    }


    public static List<String> listInvalidReceiversIds(List<IOrganization> receivers) {
        return receivers.stream()
                .filter(receiver -> !organizationIsValidReceiver(receiver))
                .map(IOrganization::getId)
                .toList();
    }


    public static String generateInvalidReceiversMessage(List<String> invalidReceiversIds) {
        var message = "Only schools can be added as receivers. Invalid organization(s) id(s): ";
        for(int i = 0; i < invalidReceiversIds.size(); i++) {
            if (i < invalidReceiversIds.size() - 1) {
                message = message.concat(invalidReceiversIds.get(i) + ", ");
            } else {
                message = message.concat(invalidReceiversIds.get(i));
            }
        }
        return message;
    }


    public static boolean organizationIsReceiver(IOrganization organization, List<IOrganization> receiversList) {
        return receiversList.isEmpty() || receiversList.contains(organization);
    }


    public static boolean organizationIsOwner(IOrganization organization, ITraineeship traineeship) {
        return traineeship.getOwner().equals(organization);
    }


    public static boolean organizationIsValidReceiver(IOrganization organization) {
        return organization.isSchool();
    }

}
