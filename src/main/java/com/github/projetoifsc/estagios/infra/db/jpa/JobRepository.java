package com.github.projetoifsc.estagios.infra.db.jpa;

import com.github.projetoifsc.estagios.core.models.IJob;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.ListPagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
interface JobRepository extends ListPagingAndSortingRepository<JobEntity, Long> {

    JobEntity save(JobEntity jobEntity);
    void delete(JobEntity job);
    void deleteById(long id);

    <T> Optional<T> findById(long id, Class<T> type);

    <T> Optional<T> findByIdAndModeratedJobsOrgId(long id, long orgId, Class<T> type);

    <T> Page<T> findAllByOwnerId(long id, Pageable pageable, Class<T> type);
    <T> List<T> findAllByExclusiveReceiversEmpty(Class<T> type);
    <T> List<T> findAllByExclusiveReceiversId(long id, Class<T> type);

    <T> List<T> findAllByOwnerIdNotAndIdNotInAndExclusiveReceiversEmptyOrExclusiveReceiversId(long ownerId, List<Long> ids, long receiverId, Class<T> type);


    boolean existsByIdAndExclusiveReceiversEmptyOrExclusiveReceiversId(long jobId, long orgId);

    <T> List<T> findAllByModeratedJobsOrgIdAndModeratedJobsStatusId(long ownerId, short statusId, Class<T> type);

    <T> List<T> findAllByOwnerIdOrModeratedJobsOrgIdAndModeratedJobsStatusId(long ownerId, long orgId, short statusId, Class<T> type);

    List<IJob> findByIdIn(List<Long> traineeshipIds);


//    <T> List<T> findAllByApprovalsOrganizationId(long orgId, Class<T> type);
//    <T> List<T> findAllByRejectionsOrganizationId(long orgId, Class<T> type);
//
//    <T> List<T> findDistinctByApprovalsOrganizationIdOrOwnerId(long orgId, long ownerId, Class<T> type);


}