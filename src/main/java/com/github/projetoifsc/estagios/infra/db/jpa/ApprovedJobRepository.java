package com.github.projetoifsc.estagios.infra.db.jpa;

import com.github.projetoifsc.estagios.core.IJob;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
interface ApprovedJobRepository extends CrudRepository<ApprovedJobEntity, Long> {

    Optional<ApprovedJobEntity> findByJobIdAndOrganizationId(long traineeshipId, long organizationId);
    List<ApprovedJobEntity> findAllByOrganizationId(long orgId);

}
