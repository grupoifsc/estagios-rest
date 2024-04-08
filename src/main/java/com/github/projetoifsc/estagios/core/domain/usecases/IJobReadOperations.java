package com.github.projetoifsc.estagios.core.domain.usecases;

import com.github.projetoifsc.estagios.core.domain.iJob;

import java.util.List;

interface IJobReadOperations {
    List<iJob> getAllCreated(String loggedId, String targetId);
    List<iJob> getAllReceived(String loggedId, String targetId);
    iJob getPrivateDetails(String organizationId, String traineeshipId);
    iJob getPublicDetails(String organizationId, String traineeshipId);
}
