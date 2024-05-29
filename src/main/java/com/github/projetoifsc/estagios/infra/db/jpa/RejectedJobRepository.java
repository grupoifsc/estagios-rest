package com.github.projetoifsc.estagios.infra.db.jpa;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
interface RejectedJobRepository extends CrudRepository<RejectedJobEntity, Long> {

    Optional<RejectedJobEntity> findByJobIdAndOrganizationId(long jobId, long orgId);
    List<RejectedJobEntity> findAllByOrganizationId(long orgId);

}
