package com.github.projetoifsc.estagios.core.implementation;

import com.github.projetoifsc.estagios.core.models.IJob;
import com.github.projetoifsc.estagios.core.IJobDAO;
import com.github.projetoifsc.estagios.core.models.IOrganization;

import java.util.List;

class Job implements IJob {

    private final IJob jobData;
    private final IJobDAO jobDB;

    private Job(IJob jobData, IJobDAO jobDB) {
        this.jobData = jobData;
        this.jobDB = jobDB;
    }

    public static Job createFromId(String id, IJobDAO jobDB) {
        var jobData = jobDB.getBasicInfo(id);
        return new Job(jobData, jobDB);
    }


    @Override
    public String getId() {
        return jobData.getId();
    }

    @Override
    public void setId(String id) {
        this.jobData.setId(id);
    }

    @Override
    public IOrganization getOwner() {
        return jobData.getOwner();
    }

    @Override
    public void setOwner(IOrganization user) {
        this.jobData.setOwner(user);
    }


    public void validateReceivers(List<IOrganization> receivers) {
        var invalidReceiversIds = listInvalidReceiversIds(receivers);
        if(invalidReceiversIds.isEmpty()) {
            return;
        }
        var exceptionMessage = generateInvalidReceiversMessage(invalidReceiversIds);
        throw new InvalidReceiverException(exceptionMessage);
    }


    private List<String> listInvalidReceiversIds(List<IOrganization> receivers) {
        return receivers.stream()
                .filter(receiver -> !OrganizationValidation.isValidReceiver(receiver))
                .map(IOrganization::getId)
                .toList();
    }


    private String generateInvalidReceiversMessage(List<String> invalidReceiversIds) {
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
