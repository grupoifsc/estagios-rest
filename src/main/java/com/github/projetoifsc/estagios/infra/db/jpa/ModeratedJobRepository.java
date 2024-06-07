package com.github.projetoifsc.estagios.infra.db.jpa;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
interface ModeratedJobRepository extends CrudRepository<ModeratedJobsEntity, Long> {

    Optional<ModeratedJobsEntity> findByJobIdAndOrganizationId(long jobId, long orgId);
    Streamable<ModeratedJobsEntity> findAllByOrganizationId(long orgId);


}
