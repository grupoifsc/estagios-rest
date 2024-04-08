package com.github.projetoifsc.estagios.core.domain.usecases;

import com.github.projetoifsc.estagios.core.domain.iJob;

interface IJobWriteOperations {
    iJob create(String organizationId, iJob traineeship);
    iJob update(String organizationId, String traineeshipId, iJob newData);
    void delete(String organizationId, String traineeshipId);
}
