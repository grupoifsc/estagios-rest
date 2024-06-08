package com.github.projetoifsc.estagios.infra.db.jpa;

import com.github.projetoifsc.estagios.core.models.ModerationProjection;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
interface ModeratedJobRepository extends CrudRepository<ModeratedJobsEntity, Long> {

    Optional<ModeratedJobsEntity> findByJobIdAndOrganizationId(long jobId, long orgId);
    <T> Optional<T> findByJobIdAndOrganizationId(long jobId, long orgId, Class<T> type);

    Streamable<ModeratedJobsEntity> findAllByOrganizationId(long orgId);


}
