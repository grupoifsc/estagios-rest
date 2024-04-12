package com.github.projetoifsc.estagios.core.domain.usecases;

import com.github.projetoifsc.estagios.core.domain.IOrganization;
import com.github.projetoifsc.estagios.core.exceptions.InvalidReceiverException;

import java.util.List;

public class ReceiverValidation {

    public static void validateReceivers(List<IOrganization> receivers) {
        var invalidReceiversIds = listInvalidReceiversIds(receivers);
        if(invalidReceiversIds.isEmpty()) {
            return;
        }
        var exceptionMessage = generateInvalidReceiversMessage(invalidReceiversIds);
        throw new InvalidReceiverException(exceptionMessage);
    }


    private static List<String> listInvalidReceiversIds(List<IOrganization> receivers) {
        return receivers.stream()
                .filter(receiver -> !OrganizationValidation.isValidReceiver(receiver))
                .map(IOrganization::getId)
                .toList();
    }


    private static String generateInvalidReceiversMessage(List<String> invalidReceiversIds) {
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


}
