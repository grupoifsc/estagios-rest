package com.github.projetoifsc.estagios.core.implementation;

import com.github.projetoifsc.estagios.core.models.IOrg;
import com.github.projetoifsc.estagios.core.models.projections.OrgSummaryProjection;

import java.util.List;

class ReceiverValidation {

    public static void validateReceivers(List<OrgSummaryProjection> receivers) {
        var invalidReceiversIds = listInvalidReceiversIds(receivers);
        if(invalidReceiversIds.isEmpty()) {
            return;
        }
        var exceptionMessage = generateInvalidReceiversMessage(invalidReceiversIds);
        throw new InvalidReceiverException(exceptionMessage);
    }


    private static List<String> listInvalidReceiversIds(List<OrgSummaryProjection> receivers) {
        return receivers.stream()
                .filter(receiver -> !OrganizationValidation.isValidReceiver(receiver))
                .map(IOrg::getId)
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
