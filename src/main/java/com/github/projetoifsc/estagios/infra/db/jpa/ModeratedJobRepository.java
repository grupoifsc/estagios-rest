package com.github.projetoifsc.estagios.infra.db.jpa;

import org.springframework.data.jpa.repository.Query;
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

    <T> List<T> findAllProjectedBy(Class<T> type);
    <T> T findFirstProjectedBy(Class<T> type);


    @Query("SELECT mod from ModeratedJobsEntity mod " +
            "LEFT join FETCH mod.job as job " +
            "LEFT join fetch job.areas " +
            "LEFT join fetch job.address " +
            "LEFT join fetch job.contact " +
            "LEFT join fetch job.format " +
            "LEFT join fetch job.level " +
            "LEFT join fetch job.owner " +
            "LEFT join fetch job.period " +
            "where mod.jobId = :jobid AND mod.orgId = :orgid")
    Optional<ModeratedJobsEntity> findwithmod(long jobid, long orgid);


}
