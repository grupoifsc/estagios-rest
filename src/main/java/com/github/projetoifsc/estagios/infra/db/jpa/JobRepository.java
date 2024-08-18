package com.github.projetoifsc.estagios.infra.db.jpa;

import com.github.projetoifsc.estagios.core.models.IJob;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListPagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
interface JobRepository extends ListPagingAndSortingRepository<JobEntity, Long> {

//    WRITE OPERATIONS

    JobEntity save(JobEntity jobEntity);

    void delete(JobEntity job);


//    READ OPERATIONS

    boolean existsByIdAndExclusiveReceiversEmptyOrExclusiveReceiversId(long jobId, long orgId);

    @Query(value =
        "SELECT j from JobEntity j LEFT JOIN FETCH j.owner as owner " +
            "LEFT JOIN FETCH j.exclusiveReceivers as receivers " +
        "WHERE j.id = :id ")
    Optional<JobEntity> findByIdBasicInfo(long id);

    @Query(value =
        "SELECT j from JobEntity j LEFT JOIN FETCH j.owner as owner " +
            "LEFT JOIN FETCH j.exclusiveReceivers as receivers " +
        "WHERE j.id IN :ids ")
    List<JobEntity> findByIdsBasicInfo(List<Long> ids);


    @Query(value =
        "SELECT j from JobEntity j " +
            "LEFT JOIN FETCH j.exclusiveReceivers as receivers " +
        "WHERE j.id = :id ")
    Optional<JobEntity> findByIdWithReceivers(long id);


    @Query(value =
        "SELECT DISTINCT j FROM JobEntity j LEFT JOIN FETCH j.owner as owner " +
            "LEFT JOIN FETCH j.areas as areas LEFT JOIN FETCH j.contact as contact " +
            "LEFT JOIN FETCH j.address as address LEFT JOIN FETCH j.format as format " +
            "LEFT JOIN FETCH j.level as level LEFT JOIN FETCH j.period as period " +
            "LEFT JOIN FETCH j.exclusiveReceivers as receivers " +
        "WHERE j.id = :id")
    Optional<JobEntity> findByIdPrivateDetails(long id);


    @Query(value =
        "SELECT DISTINCT j FROM JobEntity j LEFT JOIN FETCH j.owner as owner " +
            "LEFT JOIN FETCH j.areas as areas LEFT JOIN FETCH j.contact as contact " +
            "LEFT JOIN FETCH j.address as address LEFT JOIN FETCH j.format as format " +
            "LEFT JOIN FETCH j.level as level LEFT JOIN FETCH j.period as period " +
        "WHERE j.id = :id")
    Optional<JobEntity> findByIdPublicDetails(long id);

    <T> Optional<T> findById(long id, Class<T> type);

    List<IJob> findByIdIn(List<Long> traineeshipIds);

    <T> T findFirstProjectedBy(Class<T> projectionClass);

    <T> Page<T> findAllProjectedBy(Pageable pageable, Class<T> projectionClass);

    // Get Moderation Info == Still not used
    <T> Optional<T> findByIdAndModeratedJobsOrgId(long id, long orgId, Class<T> type);

    <T> Page<T> findAllByOwnerId(long id, Pageable pageable, Class<T> type);

    // Find all public jobs
    <T> List<T> findAllByExclusiveReceiversEmpty(Class<T> type);

    // Find all that are exclusively received by organization
    <T> List<T> findAllByExclusiveReceiversId(long id, Class<T> type);

    // * Find all pending jobs, to be moderated (depende de uma query em outro repositório)
    <T> List<T> findAllByOwnerIdNotAndIdNotInAndExclusiveReceiversEmptyOrExclusiveReceiversId(long ownerId, List<Long> ids, long receiverId, Class<T> type);

    <T> List<T> findAllByModeratedJobsOrgIdAndModeratedJobsStatusId(long ownerId, short statusId, Class<T> type);

    <T> List<T> findAllByOwnerIdOrModeratedJobsOrgIdAndModeratedJobsStatusId(long ownerId, long orgId, short statusId, Class<T> type);


    // * Get All Created By
    @Query(value =
        "SELECT DISTINCT j FROM JobEntity j LEFT JOIN FETCH j.owner as owner " +
            "LEFT JOIN FETCH j.areas as areas LEFT JOIN FETCH j.contact as contact " +
            "LEFT JOIN FETCH j.address as address LEFT JOIN FETCH j.format as format " +
            "LEFT JOIN FETCH j.level as level LEFT JOIN FETCH j.period as period " +
        "WHERE j.owner.id = :id")
    List<JobEntity> findAllByOwnerId(long id);

    @Query(value =
            "SELECT DISTINCT j FROM JobEntity j LEFT JOIN FETCH j.owner as owner " +
                " LEFT JOIN FETCH j.exclusiveReceivers as receivers " +
            "WHERE j.owner.id = :id")
    List<JobEntity> findAllByOwnerIdWithReceivers(long id);


    @Query(value =
            "SELECT DISTINCT j FROM JobEntity j LEFT JOIN FETCH j.owner as owner " +
                "LEFT JOIN FETCH j.areas as areas LEFT JOIN FETCH j.contact as contact " +
                "LEFT JOIN FETCH j.address as address LEFT JOIN FETCH j.format as format " +
                "LEFT JOIN FETCH j.level as level LEFT JOIN FETCH j.period as period " +
                "LEFT JOIN FETCH j.exclusiveReceivers as receivers " +
            "WHERE j.owner.id = :id")
    Page<JobEntity> findAllByOwnerIdWithReceiversPaginated(long id, Pageable pageable);



    // * Find all rejected OR approved by organization
    @Query(value =
            "SELECT DISTINCT j FROM JobEntity j LEFT JOIN FETCH j.owner as owner " +
                    "LEFT JOIN FETCH j.areas as areas LEFT JOIN FETCH j.contact as contact " +
                    "LEFT JOIN FETCH j.address as address LEFT JOIN FETCH j.format as format " +
                    "LEFT JOIN FETCH j.level as level LEFT JOIN FETCH j.period as period " +
                    "LEFT JOIN FETCH j.moderatedJobs as moderatedJobs " +
            "WHERE moderatedJobs.orgId = :orgId AND moderatedJobs.statusId = :statusId " )
    List<JobEntity> findAllModeratedByOrgAndStatus(long orgId, short statusId);


    // * Get All Available (created By and approved By)
    @Query(value =
        "SELECT DISTINCT j FROM JobEntity j LEFT JOIN FETCH j.owner as owner " +
            "LEFT JOIN FETCH j.areas as areas LEFT JOIN FETCH j.contact as contact " +
            "LEFT JOIN FETCH j.address as address LEFT JOIN FETCH j.format as format " +
            "LEFT JOIN FETCH j.level as level LEFT JOIN FETCH j.period as period " +
            "LEFT JOIN FETCH j.moderatedJobs as moderatedJobs " +
        "WHERE j.owner.id = :orgId OR (moderatedJobs.orgId = :orgId AND moderatedJobs.statusId = :statusId) " )
    List<JobEntity> findAllCreatedOrModeratedByOrg(long orgId, short statusId);


    // * Find all pending jobs, to be moderated
    // Que são públicos OU que o usuário está na lista de receivers MAS O usuário não moderou a vaga
    @Query(value =
        "SELECT DISTINCT j FROM JobEntity j LEFT JOIN FETCH j.owner as owner " +
            "LEFT JOIN FETCH j.areas as areas LEFT JOIN FETCH j.contact as contact " +
            "LEFT JOIN FETCH j.address as address LEFT JOIN FETCH j.format as format " +
            "LEFT JOIN FETCH j.level as level LEFT JOIN FETCH j.period as period " +
            "LEFT JOIN FETCH j.moderatedJobs as moderatedJobs " +
            "LEFT JOIN j.exclusiveReceivers as receivers " +
        "WHERE j.owner.id != :orgId " +
            "AND (j.moderatedJobs IS EMPTY OR j.id NOT IN " +
                "(SELECT DISTINCT m.jobId FROM ModeratedJobsEntity m WHERE m.orgId = :orgId)" +
            ") AND (j.exclusiveReceivers IS EMPTY OR receivers.id = :orgId) "
    )
    List<JobEntity> findAllPending (long orgId);


    @Query(value =
        "SELECT DISTINCT j FROM JobEntity j LEFT JOIN FETCH j.owner as owner " +
            "LEFT JOIN FETCH j.areas as areas LEFT JOIN FETCH j.contact as contact " +
            "LEFT JOIN FETCH j.address as address LEFT JOIN FETCH j.format as format " +
            "LEFT JOIN FETCH j.level as level LEFT JOIN FETCH j.period as period " +
            "LEFT JOIN FETCH j.moderatedJobs as moderatedJobs " +
            "LEFT JOIN j.exclusiveReceivers as receivers " +
            "WHERE j.owner.id != :orgId " +
//            "AND (j.moderatedJobs IS EMPTY OR moderatedJobs.orgId = :orgId) " +
            "AND (j.exclusiveReceivers IS EMPTY OR receivers.id = :orgId) "
    )
    List<JobEntity> findAllReceived (long orgId);


    // TODO Preciso de ajuda com uma query do banco... Não queria usar nativo
    // Mas não to conseguindo fazer ela usando o JPQL (HQL - Hibernate)
//    @Query(value =
//        "SELECT DISTINCT j, m FROM JobEntity j LEFT JOIN FETCH j.owner as owner " +
//            "LEFT JOIN FETCH j.areas as areas LEFT JOIN FETCH j.contact as contact " +
//            "LEFT JOIN FETCH j.address as address LEFT JOIN FETCH j.format as format " +
//            "LEFT JOIN FETCH j.level as level LEFT JOIN FETCH j.period as period " +
//            "LEFT JOIN j.exclusiveReceivers as receivers " +
//            "LEFT JOIN ModeratedJobsEntity m ON j.id = m.jobId AND m.orgId = :orgId " +
//            "WHERE j.owner.id != :orgId " +
//            "AND (j.exclusiveReceivers IS EMPTY OR receivers.id = :orgId) "
//    )
    @Query(value =
        "SELECT DISTINCT j FROM JobEntity j " +
            "LEFT JOIN FETCH j.owner as owner " +
            "LEFT JOIN FETCH j.areas as areas LEFT JOIN FETCH j.contact as contact " +
            "LEFT JOIN FETCH j.address as address LEFT JOIN FETCH j.format as format " +
            "LEFT JOIN FETCH j.level as level LEFT JOIN FETCH j.period as period " +
            "LEFT JOIN j.exclusiveReceivers as receivers " +
            "LEFT JOIN j.moderatedJobs as mod ON mod.jobId = j.id AND mod.orgId = :orgId " +
        "WHERE j.owner.id != :orgId " +
            "AND (j.exclusiveReceivers IS EMPTY OR receivers.id = :orgId) "
            // "AND (j.moderatedJobs IS EMPTY mod.orgId = :orgId)"
    )
    List<JobEntity> findAllReceivedWithStatus(long orgId);


}