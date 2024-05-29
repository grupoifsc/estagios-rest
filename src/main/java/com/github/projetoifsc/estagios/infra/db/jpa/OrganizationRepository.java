package com.github.projetoifsc.estagios.infra.db.jpa;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
interface OrganizationRepository extends PagingAndSortingRepository<OrganizationEntity, Long> {

    OrganizationEntity save(OrganizationEntity org);

    <T> Optional<T> findById(long id, Class<T> projectionClass);

    <T> Optional<T> findByUserCredentialsEmail(String email, Class<T> projectionClass);

    <T> Optional<T> findByUserCredentialsId(long userId, Class<T> projectionClass);

    void delete(OrganizationEntity org);
    void deleteById(long id);

    <T> Optional<T> findFirstBy(Class<T> projectionClass);

    <T> Page<T> findAllProjectedBy(Pageable pageable, Class<T> projectionClass);
    <T> Page<T> findAllByIe(boolean ie, Pageable pageable, Class<T> projectionClass);
    <T> List<T> findAllByIdIn(List<Long> ids, Class<T> projectionClass);
    <T> List<T> findAllByExclusiveReceivedJobsId(long jobId, Class<T> projectionClass);

}
